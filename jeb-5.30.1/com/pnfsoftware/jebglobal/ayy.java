package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.List;

class ayy {
   char q;
   String RF;
   String xK;
   String Dw;
   static List Uv = new ArrayList();

   ayy(char var1, String var2, String var3, String var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
   }

   static {
      Uv.add(new ayy('t', "std", "std", null));
      Uv.add(new ayy('a', "std::allocator", "std::allocator", "allocator"));
      Uv.add(new ayy('b', "std::basic_string", "std::basic_string", "basic_string"));
      Uv.add(new ayy('s', "std::string", "std::basic_string<char, std::char_traits<char>, std::allocator<char> >", "basic_string"));
      Uv.add(new ayy('i', "std::istream", "std::basic_istream<char, std::char_traits<char> >", "basic_istream"));
      Uv.add(new ayy('o', "std::ostream", "std::basic_ostream<char, std::char_traits<char> >", "basic_ostream"));
      Uv.add(new ayy('d', "std::iostream", "std::basic_iostream<char, std::char_traits<char> >", "basic_iostream"));
   }
}
