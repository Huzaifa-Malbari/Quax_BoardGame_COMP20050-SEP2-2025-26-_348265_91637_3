javac -cp "junit\lib\junit-platform-console-standalone-1.10.2.jar" --module-path "javafx\lib" --add-modules javafx.controls,javafx.fxml -d out src\main\java\hio\qss\*.java src\test\java\hio\qss\*.java

java --module-path "javafx\lib" --add-modules javafx.controls,javafx.fxml -jar junit\lib\junit-platform-console-standalone-1.10.2.jar execute --class-path out --scan-class-path