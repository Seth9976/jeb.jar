package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class bat extends CodeDocumentPart {
   bay q;
   private List RF;

   public bat(long var1, bay var3) {
      super(var1);
      this.q = var3;
   }

   public bat() {
      this(0L, null);
   }

   void q(String var1) {
      if (var1 != null) {
         if (this.RF == null) {
            this.RF = new ArrayList();
         }

         this.RF.add(var1);
      }
   }

   void q() {
      this.RF = null;
   }

   boolean RF() {
      return this.RF != null && !this.RF.isEmpty();
   }

   List xK() {
      return this.RF == null ? Collections.emptyList() : this.RF;
   }

   List Dw() {
      List var1 = this.xK();
      this.RF = null;
      return var1;
   }

   public bay Uv() {
      return this.q;
   }
}
