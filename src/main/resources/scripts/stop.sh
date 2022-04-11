#!/bin/sh
echo "********************************************************************************************"
echo "Checking for running processes"

declare -a PIDS
file="pids.tmp"
processCount=0

ps -fu $USER | grep -w "spring-mysql" | grep -v grep |awk '{print $2}' > $file

if [ -f "$file" ];
then
    PIDS=$(cat $file);
    for element in ${PIDS[@]}
    do
        ((processCount = processCount + 1))
        echo "Stopping process id [$element]"
        kill -15 $element
    done

    WAIT_COUNT=0
    MAX_WAIT_COUNT=30
    for element in ${PIDS[@]}
    do
        lineCount=($(ps -fu $USER | grep $element | grep -v grep |grep -v stop.sh | wc -l))
        while [[ $lineCount -ne 0 ]]; do
            echo -ne "."
            sleep 1
            lineCount=($(ps -fu $USER | grep $element | grep -v grep |grep -v stop.sh | wc -l))
            ((WAIT_COUNT = WAIT_COUNT + 1))

                if [ "$WAIT_COUNT" = "$MAX_WAIT_COUNT" ];
                then
                    lineCount=0
                    echo -e "\nWait count exceeded!"

                    ps -fu $USER | grep -w "spring-mysql" | grep -v grep |awk '{print $2}' > $file
                    PIDS=$(cat $file);

                    for element in ${PIDS[@]}
                    do
                        echo "Force stop process id [$element]"
                        kill -9 $element
                    done
                    rm process*.pid
                    echo -e "remove process pid file!"
                fi
        done
        echo -e "\nProcess id [$element] stopped"
    done

    echo "Stopped processes count $processCount"
    echo "Stop all command completed successfully"
    rm $file
    echo "********************************************************************************************"
    exit 0
else
    echo "WARN: Unable to stop the processes"
    echo "********************************************************************************************"
    exit 1
fi

