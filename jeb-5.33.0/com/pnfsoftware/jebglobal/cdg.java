package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class cdg implements IJavaOperatorFactory {
   private static final ILogger PR = GlobalLog.getLogger(cdg.class);
   @SerId(1)
   Map pC;
   @SerId(2)
   IJavaTypeFactory A;
   @SerId(3)
   public final cde kS = new cde(JavaOperatorType.ADD, this);
   @SerId(4)
   public final cde wS = new cde(JavaOperatorType.SUB, this);
   @SerId(5)
   public final cde UT = new cde(JavaOperatorType.MUL, this);
   @SerId(6)
   public final cde E = new cde(JavaOperatorType.DIV, this);
   @SerId(7)
   public final cde sY = new cde(JavaOperatorType.REM, this);
   @SerId(8)
   public final cde ys = new cde(JavaOperatorType.AND, this);
   @SerId(9)
   public final cde ld = new cde(JavaOperatorType.OR, this);
   @SerId(10)
   public final cde gp = new cde(JavaOperatorType.XOR, this);
   @SerId(11)
   public final cde oT = new cde(JavaOperatorType.SHL, this);
   @SerId(12)
   public final cde fI = new cde(JavaOperatorType.SHR, this);
   @SerId(13)
   public final cde WR = new cde(JavaOperatorType.USHR, this);
   @SerId(14)
   public final cde NS = new cde(JavaOperatorType.NEG, this);
   @SerId(15)
   public final cde vP = new cde(JavaOperatorType.NOT, this);
   @SerId(16)
   public final cde xC = new cde(JavaOperatorType.LOG_IDENT, this);
   @SerId(17)
   public final cde ED = new cde(JavaOperatorType.LOG_NOT, this);
   @SerId(18)
   public final cde Sc = new cde(JavaOperatorType.LOG_OR, this);
   @SerId(19)
   public final cde ah = new cde(JavaOperatorType.LOG_AND, this);
   @SerId(20)
   public final cde eP = new cde(JavaOperatorType.INSTANCEOF, this);
   @SerId(21)
   public final cde UO = new cde(JavaOperatorType.EQ, this);
   @SerId(22)
   public final cde Ab = new cde(JavaOperatorType.NE, this);
   @SerId(23)
   public final cde rl = new cde(JavaOperatorType.LT, this);
   @SerId(24)
   public final cde z = new cde(JavaOperatorType.GE, this);
   @SerId(25)
   public final cde Ek = new cde(JavaOperatorType.GT, this);
   @SerId(26)
   public final cde hK = new cde(JavaOperatorType.LE, this);
   @SerId(27)
   public final cde Er = new cde(JavaOperatorType.CAST_TO_BYTE, this);
   @SerId(28)
   public final cde FE = new cde(JavaOperatorType.CAST_TO_CHAR, this);
   @SerId(29)
   public final cde Aj = new cde(JavaOperatorType.CAST_TO_SHORT, this);
   @SerId(30)
   public final cde EX = new cde(JavaOperatorType.CAST_TO_INT, this);
   @SerId(31)
   public final cde LM = new cde(JavaOperatorType.CAST_TO_LONG, this);
   @SerId(32)
   public final cde mv = new cde(JavaOperatorType.CAST_TO_FLOAT, this);
   @SerId(33)
   public final cde sO = new cde(JavaOperatorType.CAST_TO_DOUBLE, this);
   @SerId(34)
   public final cde os = new cde(JavaOperatorType.CAST_TO_BOOLEAN, this);
   @SerId(35)
   public final cde Cu = new cde(JavaOperatorType.CONCAT, this);
   @SerId(36)
   public cde hZ = new cde(JavaOperatorType.COND_EXP, this);
   @SerId(37)
   public cde UW = new cde(JavaOperatorType.CAST_CONVERSION, this);

   @SerCustomInitPostGraph
   private void pC() {
      if (this.hZ == null) {
         this.hZ = new cde(JavaOperatorType.COND_EXP, this);
      }

      if (this.UW == null) {
         this.UW = new cde(JavaOperatorType.CAST_CONVERSION, this);
      }
   }

   public cdg(cdk var1) {
      this.pC = new HashMap();
      this.A = var1;
   }

   @Override
   public IJavaTypeFactory getTypeFactory() {
      return this.A;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public IJavaOperator getStandardOperator(JavaOperatorType var1) {
      switch (var1) {
         case ADD:
            return this.kS;
         case AND:
            return this.ys;
         case CAST_TO_BOOLEAN:
            return this.os;
         case CAST_TO_BYTE:
            return this.Er;
         case CAST_TO_CHAR:
            return this.FE;
         case CAST_TO_DOUBLE:
            return this.sO;
         case CAST_TO_FLOAT:
            return this.mv;
         case CAST_TO_INT:
            return this.EX;
         case CAST_TO_LONG:
            return this.LM;
         case CAST_TO_SHORT:
            return this.Aj;
         case CONCAT:
            return this.Cu;
         case COND_EXP:
            return this.hZ;
         case DIV:
            return this.E;
         case EQ:
            return this.UO;
         case GE:
            return this.z;
         case GT:
            return this.Ek;
         case INSTANCEOF:
            return this.eP;
         case LE:
            return this.hK;
         case LOG_AND:
            return this.ah;
         case LOG_IDENT:
            return this.xC;
         case LOG_NOT:
            return this.ED;
         case LOG_OR:
            return this.Sc;
         case LT:
            return this.rl;
         case MUL:
            return this.UT;
         case NE:
            return this.Ab;
         case NEG:
            return this.NS;
         case NOT:
            return this.vP;
         case OR:
            return this.ld;
         case REM:
            return this.sY;
         case SHL:
            return this.oT;
         case SHR:
            return this.fI;
         case SUB:
            return this.wS;
         case USHR:
            return this.WR;
         case XOR:
            return this.gp;
         case CAST_CONVERSION:
            return this.UW;
         case CAST_TO_OBJECT:
            throw new RuntimeException("CAST_TO_OBJECT does not map to a standard operator");
         default:
            throw new RuntimeException("Unknown operator: " + var1);
      }
   }

   public synchronized IJavaOperator pC(IJavaType var1) {
      if (!var1.isObject()) {
         if (var1.isByte()) {
            return this.Er;
         } else if (var1.isChar()) {
            return this.FE;
         } else if (var1.isShort()) {
            return this.Aj;
         } else if (var1.isInt()) {
            return this.EX;
         } else if (var1.isLong()) {
            return this.LM;
         } else if (var1.isFloat()) {
            return this.mv;
         } else if (var1.isDouble()) {
            return this.sO;
         } else if (var1.isBoolean()) {
            Object[] var10000 = new Object[0];
            return this.os;
         } else {
            throw new RuntimeException("Cannot create cast operator to: " + var1);
         }
      } else {
         cde var2 = (cde)this.pC.get(var1);
         if (var2 == null) {
            var2 = new cde(var1, this);
            this.pC.put(var1, var2);
         }

         return var2;
      }
   }

   @Override
   public IJavaOperator createCastOperator(IJavaType var1) {
      return this.pC(var1);
   }
}
