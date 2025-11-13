package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class awh {
   String pC;
   String A;
   int kS;
   private static Map wS = new HashMap();

   public awh(String var1, String var2, int var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   static awh pC(String var0) {
      return (awh)wS.get(var0);
   }

   static {
      ArrayList var0 = new ArrayList();
      var0.add(new awh("aN", "&=", 2));
      var0.add(new awh("aS", "=", 2));
      var0.add(new awh("aa", "&&", 2));
      var0.add(new awh("ad", "&", 1));
      var0.add(new awh("an", "&", 2));
      var0.add(new awh("at", "alignof", 1));
      var0.add(new awh("aw", "co_await ", 1));
      var0.add(new awh("az", "alignof", 1));
      var0.add(new awh("cc", "const_cast", 2));
      var0.add(new awh("cl", "()", 2));
      var0.add(new awh("cm", ",", 2));
      var0.add(new awh("co", "~", 1));
      var0.add(new awh("dV", "/=", 2));
      var0.add(new awh("dX", "[...]=", 3));
      var0.add(new awh("da", "delete[] ", 1));
      var0.add(new awh("dc", "dynamic_cast", 2));
      var0.add(new awh("de", "*", 1));
      var0.add(new awh("di", "=", 2));
      var0.add(new awh("dl", "delete ", 1));
      var0.add(new awh("ds", ".*", 2));
      var0.add(new awh("dt", ".", 2));
      var0.add(new awh("dv", "/", 2));
      var0.add(new awh("dx", "]=", 2));
      var0.add(new awh("eO", "^=", 2));
      var0.add(new awh("eo", "^", 2));
      var0.add(new awh("eq", "==", 2));
      var0.add(new awh("fL", "...", 3));
      var0.add(new awh("fR", "...", 3));
      var0.add(new awh("fl", "...", 2));
      var0.add(new awh("fr", "...", 2));
      var0.add(new awh("ge", ">=", 2));
      var0.add(new awh("gs", "::", 1));
      var0.add(new awh("gt", ">", 2));
      var0.add(new awh("ix", "[]", 2));
      var0.add(new awh("lS", "<<=", 2));
      var0.add(new awh("le", "<=", 2));
      var0.add(new awh("li", "operator\"\" ", 1));
      var0.add(new awh("ls", "<<", 2));
      var0.add(new awh("lt", "<", 2));
      var0.add(new awh("mI", "-=", 2));
      var0.add(new awh("mL", "*=", 2));
      var0.add(new awh("mi", "-", 2));
      var0.add(new awh("ml", "*", 2));
      var0.add(new awh("mm", "--", 1));
      var0.add(new awh("na", "new[]", 3));
      var0.add(new awh("ne", "!=", 2));
      var0.add(new awh("ng", "-", 1));
      var0.add(new awh("nt", "!", 1));
      var0.add(new awh("nw", "new", 3));
      var0.add(new awh("nx", "noexcept", 1));
      var0.add(new awh("oR", "|=", 2));
      var0.add(new awh("oo", "||", 2));
      var0.add(new awh("or", "|", 2));
      var0.add(new awh("pL", "+=", 2));
      var0.add(new awh("pl", "+", 2));
      var0.add(new awh("pm", "->*", 2));
      var0.add(new awh("pp", "++", 1));
      var0.add(new awh("ps", "+", 1));
      var0.add(new awh("pt", "->", 2));
      var0.add(new awh("qu", "?", 3));
      var0.add(new awh("rM", "%=", 2));
      var0.add(new awh("rS", ">>=", 2));
      var0.add(new awh("rc", "reinterpret_cast", 2));
      var0.add(new awh("rm", "%", 2));
      var0.add(new awh("rs", ">>", 2));
      var0.add(new awh("sP", "sizeof...", 1));
      var0.add(new awh("sZ", "sizeof...", 1));
      var0.add(new awh("sc", "static_cast", 2));
      var0.add(new awh("ss", "<=>", 2));
      var0.add(new awh("st", "sizeof", 1));
      var0.add(new awh("sz", "sizeof", 1));
      var0.add(new awh("tr", "throw", 0));
      var0.add(new awh("tw", "throw ", 1));
      var0.add(new awh(null, null, 0));

      for (awh var2 : var0) {
         wS.put(var2.pC, var2);
      }
   }
}
