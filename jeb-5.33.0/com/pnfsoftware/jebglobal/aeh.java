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
public abstract class aeh extends adn implements ICDecompilableElement {
   @SerId(1)
   protected String pC;
   @SerId(2)
   protected int A;
   @SerId(3)
   protected boolean kS;
   @SerId(4)
   protected int wS = -1;
   @SerId(5)
   protected int UT;
   @SerId(6)
   protected String E;

   protected aeh(ICGlobalContext var1, String var2) {
      if (var1 != null && var2 != null) {
         this.pC(var1);
         this.pC = var2;
         this.UT = 1;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public String getAddress() {
      return this.pC;
   }

   @Override
   public int getFlags() {
      return this.A;
   }

   @Override
   public boolean isExternal() {
      return this.kS;
   }

   @Override
   public int getIndex() {
      return this.wS;
   }

   @Override
   public void setStatus(int var1, String var2) {
      this.UT = var1;
      this.E = var2;
   }

   @Override
   public void setStatusCode(int var1) {
      this.setStatus(var1, null);
   }

   @Override
   public int getStatusCode() {
      return this.UT;
   }

   @Override
   public String getStatusMessage() {
      return this.E;
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

   protected void kS(COutputSink var1) {
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
