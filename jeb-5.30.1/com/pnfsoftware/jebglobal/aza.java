package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.List;

class aza {
   String q;
   String RF;
   aza.eo xK;
   static List Dw = new ArrayList();

   public aza(String var1, String var2, aza.eo var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   static aza q(int var0) {
      return (aza)Dw.get(var0);
   }

   static {
      Dw.add(new aza("signed char", "signed char", aza.eo.q));
      Dw.add(new aza("bool", "boolean", aza.eo.nf));
      Dw.add(new aza("char", "byte", aza.eo.q));
      Dw.add(new aza("double", "double", aza.eo.gP));
      Dw.add(new aza("long double", "long double", aza.eo.gP));
      Dw.add(new aza("float", "float", aza.eo.gP));
      Dw.add(new aza("__float128", "__float128", aza.eo.gP));
      Dw.add(new aza("unsigned char", "unsigned char", aza.eo.q));
      Dw.add(new aza("int", "int", aza.eo.RF));
      Dw.add(new aza("unsigned int", "unsigned", aza.eo.xK));
      Dw.add(new aza(null, null, aza.eo.q));
      Dw.add(new aza("long", "long", aza.eo.Dw));
      Dw.add(new aza("unsigned long", "unsigned long", aza.eo.Uv));
      Dw.add(new aza("__int128", "__int128", aza.eo.q));
      Dw.add(new aza("unsigned __int128", "unsigned __int128", aza.eo.q));
      Dw.add(new aza(null, null, aza.eo.q));
      Dw.add(new aza(null, null, aza.eo.q));
      Dw.add(new aza(null, null, aza.eo.q));
      Dw.add(new aza("short", "short", aza.eo.q));
      Dw.add(new aza("unsigned short", "unsigned short", aza.eo.q));
      Dw.add(new aza(null, null, aza.eo.q));
      Dw.add(new aza("void", "void", aza.eo.za));
      Dw.add(new aza("wchar_t", "char", aza.eo.q));
      Dw.add(new aza("long long", "long", aza.eo.oW));
      Dw.add(new aza("unsigned long long", "unsigned long long", aza.eo.gO));
      Dw.add(new aza("...", "...", aza.eo.q));
      Dw.add(new aza("decimal32", "decimal32", aza.eo.q));
      Dw.add(new aza("decimal64", "decimal64", aza.eo.q));
      Dw.add(new aza("decimal128", "decimal128", aza.eo.q));
      Dw.add(new aza("half", "half", aza.eo.gP));
      Dw.add(new aza("char8_t", "char8_t", aza.eo.q));
      Dw.add(new aza("char16_t", "char16_t", aza.eo.q));
      Dw.add(new aza("char32_t", "char32_t", aza.eo.q));
      Dw.add(new aza("decltype(nullptr)", "decltype(nullptr)", aza.eo.q));
      Dw.add(new aza("_Float", "_Float", aza.eo.gP));
      Dw.add(new aza("std::bfloat16_t", "std::bfloat16_t", aza.eo.gP));
      Dw.add(new aza("_BitInt", "_BitInt", aza.eo.q));
      Dw.add(new aza("unsigned _BitInt", "unsigned _BitInt", aza.eo.q));
   }

   static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW,
      gO,
      nf,
      gP,
      za;
   }
}
