#ifndef EXAMPLE_H
#define EXAMPLE_H

#include <iostream>
#include <string>

using namespace std;

class textManip {
    public:
        textManip();
        virtual ~textManip();

        string getText() {return text;}
        void setText(string val) {text = val;}

        char getGlyph() {return glyph;}
        void setGlyph(char val) {glyph = val;}

        void display();

    private:
        string text;
        char glyph;

};

#endif // EXAMPLE_H