package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.List;

class awb {
   char pC;
   String A;
   String kS;
   String wS;
   static List UT = new ArrayList();

   awb(char var1, String var2, String var3, String var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
   }

   static {
      UT.add(new awb('t', "std", "std", null));
      UT.add(new awb('a', "std::allocator", "std::allocator", "allocator"));
      UT.add(new awb('b', "std::basic_string", "std::basic_string", "basic_string"));
      UT.add(new awb('s', "std::string", "std::basic_string<char, std::char_traits<char>, std::allocator<char> >", "basic_string"));
      UT.add(new awb('i', "std::istream", "std::basic_istream<char, std::char_traits<char> >", "basic_istream"));
      UT.add(new awb('o', "std::ostream", "std::basic_ostream<char, std::char_traits<char> >", "basic_ostream"));
      UT.add(new awb('d', "std::iostream", "std::basic_iostream<char, std::char_traits<char> >", "basic_iostream"));
   }
}
