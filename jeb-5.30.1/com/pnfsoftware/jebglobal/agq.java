package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICArrayElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodSimulatorUtils;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CSimulationException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class agq extends afg implements ICOperation {
   @SerId(1)
   protected ICOperator q;
   @SerId(2)
   protected ICExpression RF;
   @SerId(3)
   protected ICExpression xK;
   @SerId(4)
   protected ICExpression Dw;

   private agq() {
   }

   agq(ICOperator var1, ICExpression var2, ICExpression var3, ICExpression var4) {
      if (var1 != null && var2 != null) {
         if (var1.isUnary()) {
            if (var3 != null || var4 != null) {
               throw new IllegalArgumentException();
            }
         } else if (var1.isBinary()) {
            if (var3 == null || var4 != null) {
               throw new IllegalArgumentException();
            }
         } else if (var1.isTertiary() && (var3 == null || var4 == null)) {
            throw new IllegalArgumentException();
         }

         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
      } else {
         throw new IllegalArgumentException();
      }
   }

   agq(ICOperator var1, ICExpression var2, ICExpression var3) {
      this(var1, var2, var3, null);
   }

   agq(ICOperator var1, ICExpression var2) {
      this(var1, var2, null, null);
   }

   @Override
   public ICOperator getOperator() {
      return this.q;
   }

   @Override
   public void setOperator(ICOperator var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
      }
   }

   @Override
   public int getCountOfOperands() {
      if (this.xK == null) {
         return 1;
      } else {
         return this.Dw == null ? 2 : 3;
      }
   }

   @Override
   public ICExpression getFirstOperand() {
      return this.RF;
   }

   @Override
   public void setFirstOperand(ICExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.RF = var1;
      }
   }

   @Override
   public ICExpression getSecondOperand() {
      return this.xK;
   }

   @Override
   public void setSecondOperand(ICExpression var1) {
      this.xK = var1;
   }

   @Override
   public ICExpression getThirdOperand() {
      return this.Dw;
   }

   @Override
   public void setThirdOperand(ICExpression var1) {
      this.Dw = var1;
   }

   @Override
   public List getSubElements() {
      return ahf.q(this.RF, this.xK, this.Dw);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.RF == var1) {
         if (!(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.RF = (ICExpression)var2;
            return true;
         }
      } else if (this.xK == var1) {
         if (var2 != null && !(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.xK = (ICExpression)var2;
            return true;
         }
      } else if (this.Dw == var1) {
         if (var2 != null && !(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.Dw = (ICExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);
      if (!var1.getSourceCustomizer().generateOperation(this, var1)) {
         COperatorType var2 = this.q.getType();
         if (var2 == COperatorType.CAST) {
            if (var1.getHideCasts()) {
               this.RF.generate(var1);
            } else {
               boolean var3 = false;
               if (var3) {
                  var1.paren();
               }

               var1.paren();
               this.q.getCastType().generate(var1);
               var1.parenClose();
               if (this.RF instanceof ICExpression && !(this.RF instanceof ICIdentifier)) {
                  ;
               }

               boolean var4 = this.q(this.RF, COperatorType.Associativity.RIGHT);
               if (var4) {
                  var1.paren();
               }

               this.RF.generate(var1);
               if (var4) {
                  var1.parenClose();
               }

               if (var3) {
                  var1.parenClose();
               }
            }
         } else if (var2 == COperatorType.COND) {
            this.RF.generate(var1);
            var1.space();
            var1.append("?");
            var1.space();
            this.xK.generate(var1);
            var1.append(":");
            var1.space();
            this.Dw.generate(var1);
         } else if (var2 == COperatorType.SIZEOF) {
            var1.append(this.q.toString());
            var1.paren();
            this.RF.generate(var1);
            var1.parenClose();
         } else if (var2 == COperatorType.CUSTOM) {
            var1.append(this.q.toString());
            var1.paren();
            int var9 = this.q.getOperandCount();
            ICExpression[] var11 = new ICExpression[]{this.RF, this.xK, this.Dw};

            for (int var5 = 0; var5 < var9; var5++) {
               if (var5 > 0) {
                  var1.append(", ");
               }

               var11[var5].generate(var1);
            }

            var1.parenClose();
         } else {
            if (this.Dw != null) {
               throw new RuntimeException("Expected a unary or duary operator at this point");
            }

            ICExpression var10 = this.RF;
            ICExpression var12 = this.xK;
            if (this.q.isUnary()) {
               if (this.xK != null) {
                  throw new RuntimeException("Unary operator operation does not accept more than one operand");
               }

               var12 = var10;
               var10 = null;
            }

            if (var10 != null) {
               boolean var13 = this.q(var10, COperatorType.Associativity.LEFT);
               if (var13) {
                  var1.paren();
               }

               var10.generate(var1);
               if (var13) {
                  var1.parenClose();
               }
            }

            String var14 = this.q.toString();
            if (var14.length() > 0) {
               if (this.q.isUnary()) {
                  var1.append(var14);
               } else {
                  var1.append(" " + var14 + " ");
               }
            }

            boolean var6 = true;
            if (var10 == null && var14.length() == 0) {
               CharSequence var7 = var1.getCurrentLine().getText();
               if (var7 != null) {
                  String var8 = var7.toString();
                  if (var8.endsWith("if(") || var8.endsWith("while(")) {
                     var6 = false;
                  }
               }
            }

            if (var6) {
               var6 = this.q(var12, COperatorType.Associativity.RIGHT);
            }

            if (var6) {
               var1.paren();
            }

            var12.generate(var1);
            if (var6) {
               var1.parenClose();
            }
         }
      }

      this.RF(var1);
   }

   private boolean q(ICExpression var1, ICExpression var2) {
      return !(var2 instanceof ICAssignment)
         && !(var2 instanceof ICReturn)
         && (!(var2 instanceof ICArrayElement) || this != ((ICArrayElement)var2).getElementIndex())
         && !(var2 instanceof ICCall);
   }

   private boolean q(ICExpression var1, COperatorType.Associativity var2) {
      if (var1 instanceof ICPredicate) {
         var1 = ((ICPredicate)var1).getExpression();
      }

      ICOperator var3 = var1 instanceof ICOperation ? ((agq)var1).getOperator() : null;
      if (var3 == null) {
         return false;
      } else {
         try {
            COperatorType var4 = this.q.getType();
            COperatorType var5 = var3.getType();
            if (!var4.equals(COperatorType.AND)
               && !var4.equals(COperatorType.XOR)
               && !var4.equals(COperatorType.OR)
               && !var4.equals(COperatorType.SHL)
               && !var4.equals(COperatorType.SHR)
               && !var4.equals(COperatorType.USHR)
               && !var4.equals(COperatorType.LOG_AND)
               && !var4.equals(COperatorType.LOG_OR)) {
               int var6 = this.q.getPrecedenceDelta(var3);
               return var6 < 0 || var6 == 0 && this.q.getAssociativity() != var2;
            } else if (var5.getOperandCount() == 1) {
               return false;
            } else {
               switch (var5) {
                  case EQ:
                  case NE:
                  case GE:
                  case GT:
                  case LE:
                  case LT:
                     return false;
                  default:
                     return var4 != var3.getType()
                        || var4 != COperatorType.AND && var4 != COperatorType.OR && var4 != COperatorType.LOG_AND && var4 != COperatorType.LOG_OR;
               }
            }
         } catch (Exception var7) {
            return true;
         }
      }
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Operation;
   }

   @Override
   public boolean reverse(ICOperatorFactory var1) {
      ICOperator var2 = this.q.getReverse();
      if (var2 == null) {
         return false;
      } else {
         this.q = var2;
         return true;
      }
   }

   @Override
   public boolean mirror(ICOperatorFactory var1) {
      if (this.Dw == null && this.RF != null && this.xK != null) {
         ICOperator var2 = this.q.getMirror();
         if (var2 == null) {
            return false;
         } else {
            this.q = var2;
            ICExpression var3 = this.RF;
            this.RF = this.xK;
            this.xK = var3;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      if (this.RF == null) {
         return this.q.toString();
      } else if (this.xK == null) {
         return this.q + " " + this.RF;
      } else {
         return this.Dw == null ? "(" + this.RF + " " + this.q + " " + this.xK + ")" : this.RF + " " + this.q + " " + this.xK + this.q + " " + this.Dw;
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      return 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         agq var2 = (agq)var1;
         if (this.q == null) {
            if (var2.q != null) {
               return false;
            }
         } else if (!this.q.equals(var2.q)) {
            return false;
         }

         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
            return false;
         }

         if (this.xK == null) {
            if (var2.xK != null) {
               return false;
            }
         } else if (!this.xK.equals(var2.xK)) {
            return false;
         }

         if (this.Dw == null) {
            if (var2.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var2.Dw)) {
            return false;
         }

         return true;
      }
   }

   public agq RF() {
      agq var1 = new agq();
      var1.q = this.q;
      var1.RF = this.RF.duplicate();
      var1.xK = this.xK == null ? null : this.xK.duplicate();
      var1.Dw = this.Dw == null ? null : this.Dw.duplicate();
      super.q(var1);
      return var1;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      Long var3 = null;
      switch (this.q.getType()) {
         case EQ:
            var3 = this.RF.evaluate(var1, var2).equals(this.xK.evaluate(var1, var2)) ? 1L : 0L;
            break;
         case NE:
            var3 = this.RF.evaluate(var1, var2).equals(this.xK.evaluate(var1, var2)) ? 0L : 1L;
            break;
         case GE:
            var3 = this.RF.evaluate(var1, var2) >= this.xK.evaluate(var1, var2) ? 1L : 0L;
            break;
         case GT:
            var3 = this.RF.evaluate(var1, var2) > this.xK.evaluate(var1, var2) ? 1L : 0L;
            break;
         case LE:
            var3 = this.RF.evaluate(var1, var2) <= this.xK.evaluate(var1, var2) ? 1L : 0L;
            break;
         case LT:
            var3 = this.RF.evaluate(var1, var2) < this.xK.evaluate(var1, var2) ? 1L : 0L;
            break;
         case ADD:
            var3 = this.RF.evaluate(var1, var2) + this.xK.evaluate(var1, var2);
            break;
         case AND:
            var3 = this.RF.evaluate(var1, var2) & this.xK.evaluate(var1, var2);
            break;
         case CAST:
            int var4 = var2.getTypeSize(this.q.getCastType());
            long var5 = MathUtil.makeMask(var4 * 8);
            var3 = this.RF.evaluate(var1, var2) & var5;
            break;
         case COND:
            var3 = this.RF.evaluate(var1, var2) != 0L ? this.xK.evaluate(var1, var2) : this.Dw.evaluate(var1, var2);
         case CUSTOM:
         case SIZEOF:
         default:
            break;
         case DIV:
            var3 = this.RF.evaluate(var1, var2) / this.xK.evaluate(var1, var2);
            break;
         case LOG_AND:
            var3 = this.RF.evaluate(var1, var2) != 0L && this.xK.evaluate(var1, var2) != 0L ? 1L : 0L;
            break;
         case LOG_IDENT:
            var3 = this.RF.evaluate(var1, var2);
            break;
         case LOG_NOT:
            var3 = this.RF.evaluate(var1, var2) != 0L ? 0L : 1L;
            break;
         case LOG_OR:
            var3 = this.RF.evaluate(var1, var2) == 0L && this.xK.evaluate(var1, var2) == 0L ? 0L : 1L;
            break;
         case MUL:
            var3 = this.RF.evaluate(var1, var2) * this.xK.evaluate(var1, var2);
            break;
         case NEG:
            var3 = -this.RF.evaluate(var1, var2);
            break;
         case NOT:
            var3 = ~this.RF.evaluate(var1, var2);
            break;
         case OR:
            var3 = this.RF.evaluate(var1, var2) | this.xK.evaluate(var1, var2);
            break;
         case PTR:
            if (this.RF instanceof agh) {
               var3 = var2.readMemory(this.RF.evaluate(var1, var2), var2.getBaseTypeSize(((agh)this.RF).getType()));
            } else {
               if (!(this.RF instanceof agq)) {
                  throw new CSimulationException(Strings.ff("PTR invalid (%s)", this.RF));
               }

               ICIdentifier var7 = CMethodSimulatorUtils.getBasePointer((agq)this.RF);
               if (var7 != null) {
                  var3 = var2.readMemory(this.RF.evaluate(var1, var2), var2.getBaseTypeSize(var7.getType()));
               } else {
                  if (var2.getDefaultPointedSize() == null) {
                     throw new CSimulationException("cant find size to read for PTR operation");
                  }

                  var3 = var2.readMemory(this.RF.evaluate(var1, var2), var2.getDefaultPointedSize());
               }
            }
            break;
         case REF:
            if (!(this.RF instanceof agh)) {
               throw new CSimulationException(Strings.ff("REF on non id (%s)", this.RF));
            }

            var3 = var2.getVarAddress((agh)this.RF);
            break;
         case REM:
            var3 = this.RF.evaluate(var1, var2) % this.xK.evaluate(var1, var2);
            break;
         case SHL:
            var3 = this.RF.evaluate(var1, var2) << (int)this.xK.evaluate(var1, var2).longValue();
            break;
         case SHR:
            var3 = this.RF.evaluate(var1, var2) >> (int)this.xK.evaluate(var1, var2).longValue();
            break;
         case SUB:
            var3 = this.RF.evaluate(var1, var2) - this.xK.evaluate(var1, var2);
            break;
         case USHR:
            var3 = this.RF.evaluate(var1, var2) >>> (int)this.xK.evaluate(var1, var2).longValue();
            break;
         case XOR:
            var3 = this.RF.evaluate(var1, var2) ^ this.xK.evaluate(var1, var2);
      }

      if (var3 == null) {
         throw new CSimulationException(Strings.ff("TBI: operator (%s)", this.q));
      } else {
         return var3;
      }
   }
}
