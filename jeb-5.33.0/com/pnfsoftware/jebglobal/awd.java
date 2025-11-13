package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.List;

class awd {
   String pC;
   String A;
   awd.Av kS;
   static List wS = new ArrayList();

   public awd(String var1, String var2, awd.Av var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   static awd pC(int var0) {
      return (awd)wS.get(var0);
   }

   static {
      wS.add(new awd("signed char", "signed char", awd.Av.pC));
      wS.add(new awd("bool", "boolean", awd.Av.ys));
      wS.add(new awd("char", "byte", awd.Av.pC));
      wS.add(new awd("double", "double", awd.Av.ld));
      wS.add(new awd("long double", "long double", awd.Av.ld));
      wS.add(new awd("float", "float", awd.Av.ld));
      wS.add(new awd("__float128", "__float128", awd.Av.ld));
      wS.add(new awd("unsigned char", "unsigned char", awd.Av.pC));
      wS.add(new awd("int", "int", awd.Av.A));
      wS.add(new awd("unsigned int", "unsigned", awd.Av.kS));
      wS.add(new awd(null, null, awd.Av.pC));
      wS.add(new awd("long", "long", awd.Av.wS));
      wS.add(new awd("unsigned long", "unsigned long", awd.Av.UT));
      wS.add(new awd("__int128", "__int128", awd.Av.pC));
      wS.add(new awd("unsigned __int128", "unsigned __int128", awd.Av.pC));
      wS.add(new awd(null, null, awd.Av.pC));
      wS.add(new awd(null, null, awd.Av.pC));
      wS.add(new awd(null, null, awd.Av.pC));
      wS.add(new awd("short", "short", awd.Av.pC));
      wS.add(new awd("unsigned short", "unsigned short", awd.Av.pC));
      wS.add(new awd(null, null, awd.Av.pC));
      wS.add(new awd("void", "void", awd.Av.gp));
      wS.add(new awd("wchar_t", "char", awd.Av.pC));
      wS.add(new awd("long long", "long", awd.Av.E));
      wS.add(new awd("unsigned long long", "unsigned long long", awd.Av.sY));
      wS.add(new awd("...", "...", awd.Av.pC));
      wS.add(new awd("decimal32", "decimal32", awd.Av.pC));
      wS.add(new awd("decimal64", "decimal64", awd.Av.pC));
      wS.add(new awd("decimal128", "decimal128", awd.Av.pC));
      wS.add(new awd("half", "half", awd.Av.ld));
      wS.add(new awd("char8_t", "char8_t", awd.Av.pC));
      wS.add(new awd("char16_t", "char16_t", awd.Av.pC));
      wS.add(new awd("char32_t", "char32_t", awd.Av.pC));
      wS.add(new awd("decltype(nullptr)", "decltype(nullptr)", awd.Av.pC));
      wS.add(new awd("_Float", "_Float", awd.Av.ld));
      wS.add(new awd("std::bfloat16_t", "std::bfloat16_t", awd.Av.ld));
      wS.add(new awd("_BitInt", "_BitInt", awd.Av.pC));
      wS.add(new awd("unsigned _BitInt", "unsigned _BitInt", awd.Av.pC));
   }

   static enum Av {
      pC,
      A,
      kS,
      wS,
      UT,
      E,
      sY,
      ys,
      ld,
      gp;
   }
}
