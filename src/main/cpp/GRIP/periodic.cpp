#include <stdio.h>
//#include <WPILib.h>
//#include <opencv/networktables.h>


int main() {

    NetworkTable *table;

    table = NetworkTable::SetClient();
    table = NetworkTable::SetIP("10.49.8.10"); //roboRIO
    table = NetworkTable::Initialize();
    table = NetworkTable::getDatabase("database");



    while (1) {
        //post variables to NT
    }

    return 0
}