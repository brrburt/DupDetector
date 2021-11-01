#include "example.h"
#include <iomanip>

textManip::textManip() {

    text = "default";
    glyph = 'A';
}

textManip::~textManip() {}

void textManip::display() {
    cout << this->text << endl;
}