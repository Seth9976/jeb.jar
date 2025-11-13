package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import java.util.HashMap;
import java.util.Map;

public class ccq extends AbstractDOptimizer {
   private static final Map pC = new HashMap();

   @Override
   public int perform() {
      ccq.Av var1 = new ccq.Av();

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IDInstruction var5 = (IDInstruction)var3.get(var4);
            var5.visitInstruction(var1);
         }
      }

      if (var1.pC > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1.pC;
   }

   static {
      pC.put("Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;", "Z");
      pC.put("Ljava/lang/Byte;->TYPE:Ljava/lang/Class;", "B");
      pC.put("Ljava/lang/Character;->TYPE:Ljava/lang/Class;", "C");
      pC.put("Ljava/lang/Short;->TYPE:Ljava/lang/Class;", "S");
      pC.put("Ljava/lang/Integer;->TYPE:Ljava/lang/Class;", "I");
      pC.put("Ljava/lang/Long;->TYPE:Ljava/lang/Class;", "J");
      pC.put("Ljava/lang/Float;->TYPE:Ljava/lang/Class;", "F");
      pC.put("Ljava/lang/Double;->TYPE:Ljava/lang/Class;", "D");
   }

   class Av implements IDVisitor {
      int pC;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDCallInfo var4) {
            if (var4.getMethodSignature().equals("Ljava/lang/reflect/Array;->newInstance(Ljava/lang/Class;I)Ljava/lang/Object;")
               && var4.getArgument(0) instanceof IDStaticField var5) {
               IJavaType var9 = null;
               if (var5.isTypeClass()) {
                  var9 = ccq.this.tf.createType("[" + var5.getClassSignature());
               } else if (var5.getFieldname().equals("TYPE")) {
                  String var7 = var5.getNativeField(ccq.this.g).getSignature(false);
                  String var8 = (String)ccq.pC.get(var7);
                  if (var8 != null) {
                     var9 = ccq.this.tf.createType("[" + var8);
                  }
               }

               if (var9 != null) {
                  IDNewArrayInfo var10 = ccq.this.ctx.createNewArrayInfo(var9, var4.getArgument(1), null);
                  if (var2.replaceSubExpression(var1, var10)) {
                     var3.setReplacedNode(var10);
                     this.pC++;
                  }
               }
            }
         }
      }
   }
}
