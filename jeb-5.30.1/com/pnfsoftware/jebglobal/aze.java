package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class aze {
   String q;
   String RF;
   int xK;
   private static Map Dw = new HashMap();

   public aze(String var1, String var2, int var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   static aze q(String var0) {
      return (aze)Dw.get(var0);
   }

   static {
      ArrayList var0 = new ArrayList();
      var0.add(new aze("aN", "&=", 2));
      var0.add(new aze("aS", "=", 2));
      var0.add(new aze("aa", "&&", 2));
      var0.add(new aze("ad", "&", 1));
      var0.add(new aze("an", "&", 2));
      var0.add(new aze("at", "alignof", 1));
      var0.add(new aze("aw", "co_await ", 1));
      var0.add(new aze("az", "alignof", 1));
      var0.add(new aze("cc", "const_cast", 2));
      var0.add(new aze("cl", "()", 2));
      var0.add(new aze("cm", ",", 2));
      var0.add(new aze("co", "~", 1));
      var0.add(new aze("dV", "/=", 2));
      var0.add(new aze("dX", "[...]=", 3));
      var0.add(new aze("da", "delete[] ", 1));
      var0.add(new aze("dc", "dynamic_cast", 2));
      var0.add(new aze("de", "*", 1));
      var0.add(new aze("di", "=", 2));
      var0.add(new aze("dl", "delete ", 1));
      var0.add(new aze("ds", ".*", 2));
      var0.add(new aze("dt", ".", 2));
      var0.add(new aze("dv", "/", 2));
      var0.add(new aze("dx", "]=", 2));
      var0.add(new aze("eO", "^=", 2));
      var0.add(new aze("eo", "^", 2));
      var0.add(new aze("eq", "==", 2));
      var0.add(new aze("fL", "...", 3));
      var0.add(new aze("fR", "...", 3));
      var0.add(new aze("fl", "...", 2));
      var0.add(new aze("fr", "...", 2));
      var0.add(new aze("ge", ">=", 2));
      var0.add(new aze("gs", "::", 1));
      var0.add(new aze("gt", ">", 2));
      var0.add(new aze("ix", "[]", 2));
      var0.add(new aze("lS", "<<=", 2));
      var0.add(new aze("le", "<=", 2));
      var0.add(new aze("li", "operator\"\" ", 1));
      var0.add(new aze("ls", "<<", 2));
      var0.add(new aze("lt", "<", 2));
      var0.add(new aze("mI", "-=", 2));
      var0.add(new aze("mL", "*=", 2));
      var0.add(new aze("mi", "-", 2));
      var0.add(new aze("ml", "*", 2));
      var0.add(new aze("mm", "--", 1));
      var0.add(new aze("na", "new[]", 3));
      var0.add(new aze("ne", "!=", 2));
      var0.add(new aze("ng", "-", 1));
      var0.add(new aze("nt", "!", 1));
      var0.add(new aze("nw", "new", 3));
      var0.add(new aze("nx", "noexcept", 1));
      var0.add(new aze("oR", "|=", 2));
      var0.add(new aze("oo", "||", 2));
      var0.add(new aze("or", "|", 2));
      var0.add(new aze("pL", "+=", 2));
      var0.add(new aze("pl", "+", 2));
      var0.add(new aze("pm", "->*", 2));
      var0.add(new aze("pp", "++", 1));
      var0.add(new aze("ps", "+", 1));
      var0.add(new aze("pt", "->", 2));
      var0.add(new aze("qu", "?", 3));
      var0.add(new aze("rM", "%=", 2));
      var0.add(new aze("rS", ">>=", 2));
      var0.add(new aze("rc", "reinterpret_cast", 2));
      var0.add(new aze("rm", "%", 2));
      var0.add(new aze("rs", ">>", 2));
      var0.add(new aze("sP", "sizeof...", 1));
      var0.add(new aze("sZ", "sizeof...", 1));
      var0.add(new aze("sc", "static_cast", 2));
      var0.add(new aze("ss", "<=>", 2));
      var0.add(new aze("st", "sizeof", 1));
      var0.add(new aze("sz", "sizeof", 1));
      var0.add(new aze("tr", "throw", 0));
      var0.add(new aze("tw", "throw ", 1));
      var0.add(new aze(null, null, 0));

      for (aze var2 : var0) {
         Dw.put(var2.q, var2);
      }
   }
}
