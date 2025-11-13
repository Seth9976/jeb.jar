package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuFrame;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.Map;

public class btn implements IDEmuFrame {
   private String pC;
   private Map A = new HashMap();
   private int kS;
   private Integer wS;
   private int UT;
   private boolean E;
   private int sY = -1;
   private IDImm ys;

   public btn(String var1) {
      if (var1 == null) {
         var1 = "";
      }

      this.pC = var1;
   }

   @Override
   public IDEmuFrame copy() {
      btn var1 = new btn(this.pC);
      var1.A = new HashMap(this.A);
      var1.kS = this.kS;
      var1.wS = this.wS;
      var1.UT = this.UT;
      var1.E = this.E;
      var1.sY = this.sY;
      var1.ys = this.ys;
      return var1;
   }

   @Override
   public String getMethodSignature() {
      return this.pC;
   }

   @Override
   public void setPC(int var1) {
      this.kS = var1;
   }

   @Override
   public int getPC() {
      return this.kS;
   }

   public void pC(int var1) {
      this.wS = var1;
   }

   @Override
   public Integer getNextPC() {
      return this.wS;
   }

   @Override
   public int updatePC() {
      Assert.a(this.wS != null);
      this.kS = this.wS;
      this.wS = null;
      this.sY = -1;
      return this.kS;
   }

   public void A(int var1) {
      this.sY = var1;
   }

   public int pC() {
      return this.sY;
   }

   @Override
   public void setExecutionComplete(boolean var1) {
      this.E = var1;
   }

   @Override
   public boolean isExecutionComplete() {
      return this.E;
   }

   @Override
   public int getIterationCount() {
      return this.UT;
   }

   public int A() {
      return ++this.UT;
   }

   @Override
   public void setVariable(int var1, IDImm var2) throws DexDecEvaluationException {
      if (var2 == null) {
         throw new RuntimeException("Cannot assign a null value, use deleteVariable() to remove a variable");
      } else {
         IJavaType var3 = var2.getType();
         if (var3 == null) {
            throw new RuntimeException("Cannot assign an untyped value");
         } else {
            if (DUtil.isSingleSlotVarId(var1)) {
               if (var3.getSlotCount() != 1) {
                  throw new DexDecEvaluationException("Illegal assignment, destination var single-slot, the source is not");
               }
            } else {
               if (!DUtil.isDoubleSlotVarId(var1)) {
                  throw new DexDecEvaluationException("Illegal assignment");
               }

               if (var3.getSlotCount() != 2) {
                  throw new DexDecEvaluationException("Illegal assignment, destination var is double-slot, the source is not");
               }
            }

            this.A.put(var1, var2);
         }
      }
   }

   @Override
   public IDImm getVariable(int var1, boolean var2) throws DexDecEvaluationException {
      IDImm var3 = (IDImm)this.A.get(var1);
      if (var3 == null) {
         if (var2) {
            return null;
         } else {
            throw new DexDecEvaluationException("Variable is not set: " + var1);
         }
      } else {
         return var3;
      }
   }

   @Override
   public IDImm getVariable(int var1) throws DexDecEvaluationException {
      return this.getVariable(var1, false);
   }

   @Override
   public boolean hasVariable(int var1) throws DexDecEvaluationException {
      return this.getVariable(var1, true) != null;
   }

   @Override
   public boolean deleteVariable(int var1) throws DexDecEvaluationException {
      IDImm var2 = (IDImm)this.A.remove(var1);
      return var2 != null;
   }

   @Override
   public void deleteVariables() {
      this.A.clear();
   }

   @Override
   public Map getVarMap() {
      return this.A;
   }

   @Override
   public void setVarMap(Map var1) {
      this.A = var1;
   }

   @Override
   public void setRaisedException(IDImm var1) {
      this.ys = var1;
   }

   @Override
   public IDImm getRaisedException() {
      return this.ys;
   }

   public String kS(int var1) {
      if (var1 != 1) {
         return Strings.ff("%s @ 0x%X ", this.pC, this.kS);
      } else {
         StringBuilder var2 = new StringBuilder();
         Strings.ff(var2, "@0x%X", this.kS);
         if (!this.A.isEmpty()) {
            Strings.ff(var2, " : ");
            int var3 = 0;

            for (int var5 : this.A.keySet()) {
               if (var3 >= 1) {
                  Strings.ff(var2, ", ");
               }

               Strings.ff(var2, "%s=%s", DUtil.formatVarId(var5), this.A.get(var5));
               var3++;
            }
         }

         return var2.toString();
      }
   }

   @Override
   public String toString() {
      return this.kS(0);
   }
}
