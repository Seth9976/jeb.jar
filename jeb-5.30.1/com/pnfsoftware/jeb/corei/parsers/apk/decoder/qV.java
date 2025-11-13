package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.util.base.TypedContent;
import java.util.Collections;
import java.util.List;

public class qV {
   Uz q;
   String RF;
   String xK;
   TypedContent Dw;
   List Uv;
   Exception oW;

   public qV(Uz var1, String var2, TypedContent var3) {
      this(var1, var2, var3, null, null);
   }

   public qV(Uz var1, String var2, TypedContent var3, List var4, Exception var5) {
      this.q = var1;
      this.RF = var2;
      this.Dw = var3;
      this.Uv = var4;
      this.oW = var5;
   }

   public Uz q() {
      return this.q;
   }

   public boolean RF() {
      return this.xK != null;
   }

   public String xK() {
      return this.q(true);
   }

   public String q(boolean var1) {
      if (!var1) {
         return this.RF;
      } else {
         return this.xK != null ? this.xK : this.RF;
      }
   }

   void q(String var1) {
      this.xK = var1;
   }

   public TypedContent Dw() {
      return this.Dw;
   }

   public List Uv() {
      return this.Uv != null ? this.Uv : Collections.emptyList();
   }

   public Exception oW() {
      return this.oW;
   }

   public boolean gO() {
      return this.q.q();
   }

   public String nf() {
      if (this.q.q() && this.Dw != null) {
         return this.Dw.getText();
      } else {
         throw new RuntimeException();
      }
   }
}
