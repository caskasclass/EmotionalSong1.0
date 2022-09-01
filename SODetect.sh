#include <stdlib.h>

echo $OSTYPE;
#include <stdlib.h>

SCRIPTPATH="$( cd -- "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )";

SCRIPTPATH+="/lib/";

echo $SCRIPTPATH;

if [[ "$OSTYPE" == "darwin"* ]];
 then
       java -jar --enable-preview --module-path lib/ --add-modules=javafx.controls,javafx.media,javafx.fxml,javafx.graphics EmotionalSong1.0.jar
elif [[ "$OSTYPE" == "win"* ]];
then 
    echo "ok";
fi
