package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDElement;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDIndex;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDReferenceType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class bvk {
   private TreeMap q;

   public bvk(boolean var1) {
      if (var1) {
         this.q = new TreeMap();
      }
   }

   public Map q() {
      if (this.q == null) {
         throw new IllegalStateException();
      } else {
         return Collections.unmodifiableSortedMap(this.q);
      }
   }

   public boolean q(IDInstruction var1, IDInstruction var2) {
      DOpcodeType var3 = var1.getOpcode();
      DOpcodeType var4 = var2.getOpcode();
      if (var3 != var4) {
         return false;
      } else {
         boolean var5 = var1.getDefinedVariable() != null;
         return this.q(var1.getOperand1(), var2.getOperand1(), var5) && this.q(var1.getOperand2(), var2.getOperand2(), false);
      }
   }

   private boolean q(IDElement var1, IDElement var2) {
      return this.q(var1, var2, false);
   }

   private boolean q(IDElement var1, IDElement var2, boolean var3) {
      if (var1 == null) {
         return var2 == null;
      } else if (var2 == null) {
         return false;
      } else if (var1.getClass() != var2.getClass()) {
         return false;
      } else if (var1 instanceof IDExpression var9) {
         return this.q(var9, (IDExpression)var2, var3);
      } else if (var1 instanceof IDIndex var8) {
         return var8.getValue() == ((IDIndex)var2).getValue();
      } else if (var1 instanceof IDTarget) {
         return true;
      } else if (var1 instanceof IDSwitchData var4) {
         IDSwitchData var5 = (IDSwitchData)var2;
         if (!var4.getCases().equals(var5.getCases())) {
            return false;
         } else {
            for (Object var7 : var4.getCases()) {
               if (!this.q(var4.getTargetForCase(var7), var5.getTargetForCase(var7))) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   private boolean q(IDExpression var1, IDExpression var2) {
      return this.q(var1, var2, false);
   }

   private boolean q(IDExpression var1, IDExpression var2, boolean var3) {
      if (var1 == null) {
         return var2 == null;
      } else if (var2 == null) {
         return false;
      } else if (var1.getClass() != var2.getClass()) {
         return false;
      } else if (var1 instanceof IDImm || var1 instanceof IDReferenceType || var1 instanceof IDStaticField) {
         return var1.equalsEx(var2, false);
      } else if (var1 instanceof IDVar var12) {
         int var17 = var12.getId();
         int var19 = ((IDVar)var2).getId();
         if (this.q == null) {
            return var17 == var19;
         } else if (var17 == var19 || var3 || this.q.containsKey(var17) && this.q.containsValue(var19)) {
            Integer var21 = (Integer)this.q.get(var17);
            if (var21 == null) {
               this.q.put(var17, var19);
            } else if (var21 != var19) {
               return false;
            }

            return true;
         } else {
            return false;
         }
      } else if (var1 instanceof IDArrayElt var11) {
         IDArrayElt var16 = (IDArrayElt)var2;
         return !this.q(var11.getArray(), var16.getArray()) ? false : this.q(var11.getIndex(), var16.getIndex());
      } else if (var1 instanceof IDInstanceField var10) {
         IDInstanceField var15 = (IDInstanceField)var2;
         return !this.q(var10.getInstance(), var15.getInstance()) ? false : this.q(var10.getIndex(), var15.getIndex());
      } else if (var1 instanceof IDOperation var9) {
         IDOperation var14 = (IDOperation)var2;
         if (!Objects.equals(var9.getOperator(), var14.getOperator())) {
            return false;
         } else if (!this.q(var9.getOperand1(), var14.getOperand1())) {
            return false;
         } else {
            return !this.q(var9.getOperand2(), var14.getOperand2()) ? false : this.q(var9.getCondPredicate(), var14.getCondPredicate());
         }
      } else if (var1 instanceof IDCallInfo var8) {
         IDCallInfo var13 = (IDCallInfo)var2;
         if (var8.getInvokeType() != var13.getInvokeType()) {
            return false;
         } else if (!var8.getMethodSignature().equals(var13.getMethodSignature())) {
            return false;
         } else {
            int var18 = var8.getCountOfArguments();
            if (var18 != var13.getCountOfArguments()) {
               return false;
            } else {
               for (int var20 = 0; var20 < var18; var20++) {
                  if (!this.q(var8.getArgument(var20), var13.getArgument(var20))) {
                     return false;
                  }
               }

               return true;
            }
         }
      } else if (var1 instanceof IDNewArrayInfo var4) {
         IDNewArrayInfo var5 = (IDNewArrayInfo)var2;
         if (!var4.getType().equals(var5.getType())) {
            return false;
         } else if (!this.q(var4.getSize(), var5.getSize())) {
            return false;
         } else {
            int var6 = var4.getCountOfInitialValues();
            if (var6 != var5.getCountOfInitialValues()) {
               return false;
            } else {
               for (int var7 = 0; var7 < var6; var7++) {
                  if (!this.q(var4.getInitialValue(var7), var5.getInitialValue(var7))) {
                     return false;
                  }
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }
}
