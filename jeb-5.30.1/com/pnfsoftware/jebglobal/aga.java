package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecompilableElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public abstract class aga extends afg implements ICDecompilableElement {
   @SerId(1)
   protected String q;
   @SerId(2)
   protected int RF;
   @SerId(3)
   protected boolean xK;
   @SerId(4)
   protected int Dw = -1;
   @SerId(5)
   protected int Uv;
   @SerId(6)
   protected String oW;
   private static final String gO = "__$internal$_NOTES";

   protected aga(ICGlobalContext var1, String var2) {
      if (var1 != null && var2 != null) {
         this.q(var1);
         this.q = var2;
         this.Uv = 1;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public String getAddress() {
      return this.q;
   }

   @Override
   public int getFlags() {
      return this.RF;
   }

   @Override
   public boolean isExternal() {
      return this.xK;
   }

   @Override
   public int getIndex() {
      return this.Dw;
   }

   @Override
   public void setStatus(int var1, String var2) {
      this.Uv = var1;
      this.oW = var2;
   }

   @Override
   public void setStatusCode(int var1) {
      this.setStatus(var1, null);
   }

   @Override
   public int getStatusCode() {
      return this.Uv;
   }

   @Override
   public String getStatusMessage() {
      return this.oW;
   }

   @Override
   public void reset() {
      this.setStatusCode(4);
   }

   @Override
   public boolean addDecompilationNote(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         Object var3 = this.getData("__$internal$_NOTES");
         Object var2;
         if (!(var3 instanceof List)) {
            var2 = new ArrayList();
            this.setData("__$internal$_NOTES", var2);
         } else {
            var2 = (List)var3;
         }

         if (var2.contains(var1)) {
            return true;
         } else {
            var2.add(var1);
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public List getDecompilationNotes() {
      return !(this.getData("__$internal$_NOTES") instanceof List var2) ? Collections.emptyList() : Collections.unmodifiableList(var2);
   }

   protected void xK(COutputSink var1) {
      List var2 = this.getDecompilationNotes();
      if (!var2.isEmpty()) {
         String var3;
         if (var2.size() == 1) {
            var3 = (String)var2.get(0);
         } else {
            StringBuilder var4 = new StringBuilder();

            for (String var6 : this.getDecompilationNotes()) {
               var4.append("- ").append(var6).append("\n");
            }

            var3 = var4.toString();
         }

         var1.appendMultiLineComment(var3, true, true);
      }
   }

   @Override
   public String toString() {
      return "elt:" + this.getAddress();
   }
}
