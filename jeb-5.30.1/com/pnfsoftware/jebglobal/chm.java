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

public class chm extends AbstractDOptimizer {
   private static final String q = "Ljava/lang/reflect/Array;->newInstance(Ljava/lang/Class;I)Ljava/lang/Object;";
   private static final Map RF = new HashMap();

   @Override
   public int perform() {
      chm.eo var1 = new chm.eo();

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IDInstruction var5 = (IDInstruction)var3.get(var4);
            var5.visitInstruction(var1);
         }
      }

      if (var1.q > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1.q;
   }

   static {
      RF.put("Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;", "Z");
      RF.put("Ljava/lang/Byte;->TYPE:Ljava/lang/Class;", "B");
      RF.put("Ljava/lang/Character;->TYPE:Ljava/lang/Class;", "C");
      RF.put("Ljava/lang/Short;->TYPE:Ljava/lang/Class;", "S");
      RF.put("Ljava/lang/Integer;->TYPE:Ljava/lang/Class;", "I");
      RF.put("Ljava/lang/Long;->TYPE:Ljava/lang/Class;", "J");
      RF.put("Ljava/lang/Float;->TYPE:Ljava/lang/Class;", "F");
      RF.put("Ljava/lang/Double;->TYPE:Ljava/lang/Class;", "D");
   }

   class eo implements IDVisitor {
      int q;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDCallInfo var4) {
            if (var4.getMethodSignature().equals("Ljava/lang/reflect/Array;->newInstance(Ljava/lang/Class;I)Ljava/lang/Object;")
               && var4.getArgument(0) instanceof IDStaticField var5) {
               IJavaType var9 = null;
               if (var5.isTypeClass()) {
                  var9 = chm.this.tf.createType("[" + var5.getClassSignature());
               } else if (var5.getFieldname().equals("TYPE")) {
                  String var7 = var5.getNativeField(chm.this.g).getSignature(false);
                  String var8 = (String)chm.RF.get(var7);
                  if (var8 != null) {
                     var9 = chm.this.tf.createType("[" + var8);
                  }
               }

               if (var9 != null) {
                  IDNewArrayInfo var10 = chm.this.ctx.createNewArrayInfo(var9, var4.getArgument(1), null);
                  if (var2.replaceSubExpression(var1, var10)) {
                     var3.setReplacedNode(var10);
                     this.q++;
                  }
               }
            }
         }
      }
   }
}
