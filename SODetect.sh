#include <stdlib.h>

echo $OSTYPE;

if [[ "$OSTYPE" == "darwin"* ]];
 then
    java -jar --enable-preview --module-path lib/ --add-modules=javafx.controls,javafx.media,javafx.fxml,javafx.graphics EmotionalSong1.0.jar
elif [[ "$OSTYPE" == "msys"* ]];
then 
    java -jar --enable-preview --module-path \"~\\lib\" --add-modules=javafx.controls,javafx.media,javafx.fxml,javafx.graphics EmotionalSong1.0.jar
fi