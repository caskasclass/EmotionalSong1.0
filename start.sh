#include <stdlib.h>
SCRIPTPATH="$( cd -- "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )";

SCRIPTPATH+="/lib/libWindows/lib";

if [[ "$OSTYPE" == "darwin"* ]];
 then
    java -jar --enable-preview --module-path lib/libMac/ --add-modules=javafx.controls,javafx.media,javafx.fxml,javafx.graphics EmotionalSong1.0.jar
elif [[ "$OSTYPE" == "msys"* ]];
then 
    java -jar --enable-preview --module-path $SCRIPTPATH --add-modules=javafx.controls,javafx.media,javafx.fxml,javafx.graphics EmotionalSong1.0.jar
fi
