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
public class cio implements IJavaOperatorFactory {
   private static final ILogger Xo = GlobalLog.getLogger(cio.class);
   @SerId(1)
   Map q;
   @SerId(2)
   IJavaTypeFactory RF;
   @SerId(3)
   public final cim xK = new cim(JavaOperatorType.ADD, this);
   @SerId(4)
   public final cim Dw = new cim(JavaOperatorType.SUB, this);
   @SerId(5)
   public final cim Uv = new cim(JavaOperatorType.MUL, this);
   @SerId(6)
   public final cim oW = new cim(JavaOperatorType.DIV, this);
   @SerId(7)
   public final cim gO = new cim(JavaOperatorType.REM, this);
   @SerId(8)
   public final cim nf = new cim(JavaOperatorType.AND, this);
   @SerId(9)
   public final cim gP = new cim(JavaOperatorType.OR, this);
   @SerId(10)
   public final cim za = new cim(JavaOperatorType.XOR, this);
   @SerId(11)
   public final cim lm = new cim(JavaOperatorType.SHL, this);
   @SerId(12)
   public final cim zz = new cim(JavaOperatorType.SHR, this);
   @SerId(13)
   public final cim JY = new cim(JavaOperatorType.USHR, this);
   @SerId(14)
   public final cim HF = new cim(JavaOperatorType.NEG, this);
   @SerId(15)
   public final cim LK = new cim(JavaOperatorType.NOT, this);
   @SerId(16)
   public final cim io = new cim(JavaOperatorType.LOG_IDENT, this);
   @SerId(17)
   public final cim qa = new cim(JavaOperatorType.LOG_NOT, this);
   @SerId(18)
   public final cim Hk = new cim(JavaOperatorType.LOG_OR, this);
   @SerId(19)
   public final cim Me = new cim(JavaOperatorType.LOG_AND, this);
   @SerId(20)
   public final cim PV = new cim(JavaOperatorType.INSTANCEOF, this);
   @SerId(21)
   public final cim oQ = new cim(JavaOperatorType.EQ, this);
   @SerId(22)
   public final cim xW = new cim(JavaOperatorType.NE, this);
   @SerId(23)
   public final cim KT = new cim(JavaOperatorType.LT, this);
   @SerId(24)
   public final cim Gf = new cim(JavaOperatorType.GE, this);
   @SerId(25)
   public final cim Ef = new cim(JavaOperatorType.GT, this);
   @SerId(26)
   public final cim cC = new cim(JavaOperatorType.LE, this);
   @SerId(27)
   public final cim sH = new cim(JavaOperatorType.CAST_TO_BYTE, this);
   @SerId(28)
   public final cim CE = new cim(JavaOperatorType.CAST_TO_CHAR, this);
   @SerId(29)
   public final cim wF = new cim(JavaOperatorType.CAST_TO_SHORT, this);
   @SerId(30)
   public final cim If = new cim(JavaOperatorType.CAST_TO_INT, this);
   @SerId(31)
   public final cim Dz = new cim(JavaOperatorType.CAST_TO_LONG, this);
   @SerId(32)
   public final cim mI = new cim(JavaOperatorType.CAST_TO_FLOAT, this);
   @SerId(33)
   public final cim jq = new cim(JavaOperatorType.CAST_TO_DOUBLE, this);
   @SerId(34)
   public final cim ui = new cim(JavaOperatorType.CAST_TO_BOOLEAN, this);
   @SerId(35)
   public final cim TX = new cim(JavaOperatorType.CONCAT, this);
   @SerId(36)
   public cim Rr = new cim(JavaOperatorType.COND_EXP, this);
   @SerId(37)
   public cim EB = new cim(JavaOperatorType.CAST_CONVERSION, this);

   @SerCustomInitPostGraph
   private void q() {
      if (this.Rr == null) {
         this.Rr = new cim(JavaOperatorType.COND_EXP, this);
      }

      if (this.EB == null) {
         this.EB = new cim(JavaOperatorType.CAST_CONVERSION, this);
      }
   }

   public cio(cis var1) {
      this.q = new HashMap();
      this.RF = var1;
   }

   @Override
   public IJavaTypeFactory getTypeFactory() {
      return this.RF;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public IJavaOperator getStandardOperator(JavaOperatorType var1) {
      switch (var1) {
         case ADD:
            return this.xK;
         case AND:
            return this.nf;
         case CAST_TO_BOOLEAN:
            return this.ui;
         case CAST_TO_BYTE:
            return this.sH;
         case CAST_TO_CHAR:
            return this.CE;
         case CAST_TO_DOUBLE:
            return this.jq;
         case CAST_TO_FLOAT:
            return this.mI;
         case CAST_TO_INT:
            return this.If;
         case CAST_TO_LONG:
            return this.Dz;
         case CAST_TO_SHORT:
            return this.wF;
         case CONCAT:
            return this.TX;
         case COND_EXP:
            return this.Rr;
         case DIV:
            return this.oW;
         case EQ:
            return this.oQ;
         case GE:
            return this.Gf;
         case GT:
            return this.Ef;
         case INSTANCEOF:
            return this.PV;
         case LE:
            return this.cC;
         case LOG_AND:
            return this.Me;
         case LOG_IDENT:
            return this.io;
         case LOG_NOT:
            return this.qa;
         case LOG_OR:
            return this.Hk;
         case LT:
            return this.KT;
         case MUL:
            return this.Uv;
         case NE:
            return this.xW;
         case NEG:
            return this.HF;
         case NOT:
            return this.LK;
         case OR:
            return this.gP;
         case REM:
            return this.gO;
         case SHL:
            return this.lm;
         case SHR:
            return this.zz;
         case SUB:
            return this.Dw;
         case USHR:
            return this.JY;
         case XOR:
            return this.za;
         case CAST_CONVERSION:
            return this.EB;
         case CAST_TO_OBJECT:
            throw new RuntimeException("CAST_TO_OBJECT does not map to a standard operator");
         default:
            throw new RuntimeException("Unknown operator: " + var1);
      }
   }

   public synchronized IJavaOperator q(IJavaType var1) {
      if (!var1.isObject()) {
         if (var1.isByte()) {
            return this.sH;
         } else if (var1.isChar()) {
            return this.CE;
         } else if (var1.isShort()) {
            return this.wF;
         } else if (var1.isInt()) {
            return this.If;
         } else if (var1.isLong()) {
            return this.Dz;
         } else if (var1.isFloat()) {
            return this.mI;
         } else if (var1.isDouble()) {
            return this.jq;
         } else if (var1.isBoolean()) {
            Object[] var10000 = new Object[0];
            return this.ui;
         } else {
            throw new RuntimeException("Cannot create cast operator to: " + var1);
         }
      } else {
         cim var2 = (cim)this.q.get(var1);
         if (var2 == null) {
            var2 = new cim(var1, this);
            this.q.put(var1, var2);
         }

         return var2;
      }
   }

   @Override
   public IJavaOperator createCastOperator(IJavaType var1) {
      return this.q(var1);
   }
}
