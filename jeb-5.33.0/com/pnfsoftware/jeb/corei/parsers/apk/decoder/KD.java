package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.util.base.TypedContent;
import java.util.Collections;
import java.util.List;

public class KD {
   Mh pC;
   String A;
   String kS;
   TypedContent wS;
   List UT;
   Exception E;

   public KD(Mh var1, String var2, TypedContent var3) {
      this(var1, var2, var3, null, null);
   }

   public KD(Mh var1, String var2, TypedContent var3, List var4, Exception var5) {
      this.pC = var1;
      this.A = var2;
      this.wS = var3;
      this.UT = var4;
      this.E = var5;
   }

   public Mh pC() {
      return this.pC;
   }

   public boolean A() {
      return this.kS != null;
   }

   public String kS() {
      return this.pC(true);
   }

   public String pC(boolean var1) {
      if (!var1) {
         return this.A;
      } else {
         return this.kS != null ? this.kS : this.A;
      }
   }

   void pC(String var1) {
      this.kS = var1;
   }

   public TypedContent wS() {
      return this.wS;
   }

   public List UT() {
      return this.UT != null ? this.UT : Collections.emptyList();
   }

   public Exception E() {
      return this.E;
   }

   public boolean sY() {
      return this.pC.pC();
   }
}
