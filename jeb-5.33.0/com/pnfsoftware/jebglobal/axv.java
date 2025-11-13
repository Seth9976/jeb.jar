package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class axv extends CodeDocumentPart {
   axz pC;
   private List A;

   public axv(long var1, axz var3) {
      super(var1);
      this.pC = var3;
   }

   public axv() {
      this(0L, null);
   }

   void pC(String var1) {
      if (var1 != null) {
         if (this.A == null) {
            this.A = new ArrayList();
         }

         this.A.add(var1);
      }
   }

   boolean pC() {
      return this.A != null && !this.A.isEmpty();
   }

   List A() {
      return this.A == null ? Collections.emptyList() : this.A;
   }

   List kS() {
      List var1 = this.A();
      this.A = null;
      return var1;
   }
}
