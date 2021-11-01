#include "example.h"

using namespace std;

int main() {
    int ticker; 
    textManip txt;
    
    string newTxt;

    cin >> newTxt;
    txt.setText(newTxt);
    
    for(ticker = 0; ticker < 5; ticker++){
        txt.display();
    }

    return 0;
}
