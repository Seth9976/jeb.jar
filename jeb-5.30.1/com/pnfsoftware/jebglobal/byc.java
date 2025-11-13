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

public class byc implements IDEmuFrame {
   private String q;
   private Map RF = new HashMap();
   private int xK;
   private Integer Dw;
   private int Uv;
   private boolean oW;
   private int gO = -1;
   private IDImm nf;

   public byc(String var1) {
      if (var1 == null) {
         var1 = "";
      }

      this.q = var1;
   }

   @Override
   public IDEmuFrame copy() {
      byc var1 = new byc(this.q);
      var1.RF = new HashMap(this.RF);
      var1.xK = this.xK;
      var1.Dw = this.Dw;
      var1.Uv = this.Uv;
      var1.oW = this.oW;
      var1.gO = this.gO;
      var1.nf = this.nf;
      return var1;
   }

   @Override
   public String getMethodSignature() {
      return this.q;
   }

   @Override
   public void setPC(int var1) {
      this.xK = var1;
   }

   @Override
   public int getPC() {
      return this.xK;
   }

   public void q(int var1) {
      this.Dw = var1;
   }

   @Override
   public Integer getNextPC() {
      return this.Dw;
   }

   @Override
   public int updatePC() {
      Assert.a(this.Dw != null);
      this.xK = this.Dw;
      this.Dw = null;
      this.gO = -1;
      return this.xK;
   }

   public void RF(int var1) {
      this.gO = var1;
   }

   public int q() {
      return this.gO;
   }

   @Override
   public void setExecutionComplete(boolean var1) {
      this.oW = var1;
   }

   @Override
   public boolean isExecutionComplete() {
      return this.oW;
   }

   @Override
   public int getIterationCount() {
      return this.Uv;
   }

   public int RF() {
      return ++this.Uv;
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

            this.RF.put(var1, var2);
         }
      }
   }

   @Override
   public IDImm getVariable(int var1, boolean var2) throws DexDecEvaluationException {
      IDImm var3 = (IDImm)this.RF.get(var1);
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
      IDImm var2 = (IDImm)this.RF.remove(var1);
      return var2 != null;
   }

   @Override
   public void deleteVariables() {
      this.RF.clear();
   }

   @Override
   public Map getVarMap() {
      return this.RF;
   }

   @Override
   public void setVarMap(Map var1) {
      this.RF = var1;
   }

   @Override
   public void setRaisedException(IDImm var1) {
      this.nf = var1;
   }

   @Override
   public IDImm getRaisedException() {
      return this.nf;
   }

   public String xK(int var1) {
      if (var1 != 1) {
         return Strings.ff("%s @ 0x%X ", this.q, this.xK);
      } else {
         StringBuilder var2 = new StringBuilder();
         Strings.ff(var2, "@0x%X", this.xK);
         if (!this.RF.isEmpty()) {
            Strings.ff(var2, " : ");
            int var3 = 0;

            for (int var5 : this.RF.keySet()) {
               if (var3 >= 1) {
                  Strings.ff(var2, ", ");
               }

               Strings.ff(var2, "%s=%s", DUtil.formatVarId(var5), this.RF.get(var5));
               var3++;
            }
         }

         return var2.toString();
      }
   }

   @Override
   public String toString() {
      return this.xK(0);
   }
}
