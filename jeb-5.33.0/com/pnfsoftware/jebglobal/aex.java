package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodSimulatorUtils;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CSimulationException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class aex extends adn implements ICOperation {
   @SerId(1)
   protected ICOperator pC;
   @SerId(2)
   protected ICExpression A;
   @SerId(3)
   protected ICExpression kS;
   @SerId(4)
   protected ICExpression wS;
   @SerId(5)
   protected List UT;

   private aex() {
   }

   aex(ICOperator var1, ICExpression var2, ICExpression var3, ICExpression var4, List var5) {
      Assert.a(var1 != null);
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
      this.kS();
   }

   aex(ICOperator var1, ICExpression var2, ICExpression var3, ICExpression var4) {
      this(var1, var2, var3, var4, null);
   }

   aex(ICOperator var1, ICExpression var2, ICExpression var3) {
      this(var1, var2, var3, null, null);
   }

   aex(ICOperator var1, ICExpression var2) {
      this(var1, var2, null, null, null);
   }

   aex(ICOperator var1) {
      this(var1, null, null, null, null);
   }

   private int kS() {
      int var1;
      if (this.A == null) {
         if (this.kS != null || this.wS != null || this.UT != null) {
            throw new IllegalStateException();
         }

         var1 = 0;
      } else if (this.kS == null) {
         if (this.wS != null || this.UT != null) {
            throw new IllegalStateException();
         }

         var1 = 1;
      } else if (this.wS == null) {
         if (this.UT != null) {
            throw new IllegalStateException();
         }

         var1 = 2;
      } else if (this.UT == null) {
         var1 = 3;
      } else {
         var1 = 3 + this.UT.size();
      }

      if (var1 != this.getCountOfOperands()) {
         throw new IllegalStateException();
      } else {
         return var1;
      }
   }

   @Override
   public int getCountOfOperands() {
      return this.pC.getOperandCount();
   }

   @Override
   public List getOperands() {
      int var1 = this.kS();
      ArrayList var2 = new ArrayList(var1);

      for (int var3 = 0; var3 < var1; var3++) {
         if (var3 == 0) {
            var2.add(this.A);
         } else if (var3 == 1) {
            var2.add(this.kS);
         } else {
            if (var3 != 2) {
               var2.addAll(this.UT);
               break;
            }

            var2.add(this.wS);
         }
      }

      return var2;
   }

   @Override
   public ICOperator getOperator() {
      return this.pC;
   }

   @Override
   public void setOperator(ICOperator var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
      }
   }

   @Override
   public ICExpression getFirstOperand() {
      return this.A;
   }

   @Override
   public void setFirstOperand(ICExpression var1) {
      this.A = var1;
   }

   @Override
   public ICExpression getSecondOperand() {
      return this.kS;
   }

   @Override
   public void setSecondOperand(ICExpression var1) {
      this.kS = var1;
   }

   @Override
   public ICExpression getThirdOperand() {
      return this.wS;
   }

   @Override
   public void setThirdOperand(ICExpression var1) {
      this.wS = var1;
   }

   @Override
   public List getExtraOperands() {
      return this.UT;
   }

   @Override
   public void setExtraOperands(List var1) {
      this.UT = var1;
   }

   @Override
   public List getSubElements() {
      List var1 = afm.pC(this.A, this.kS, this.wS);
      afm.pC(var1, this.UT);
      return var1;
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.A == var1) {
         if (var2 != null && !(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.A = (ICExpression)var2;
            return true;
         }
      } else if (this.kS == var1) {
         if (var2 != null && !(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.kS = (ICExpression)var2;
            return true;
         }
      } else if (this.wS == var1) {
         if (var2 != null && !(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.wS = (ICExpression)var2;
            return true;
         }
      } else {
         if (this.UT != null && var2 == null || var2 instanceof ICExpression) {
            for (int var3 = 0; var3 < this.UT.size(); var3++) {
               if (this.UT.get(var3) == var1) {
                  if (var2 == null) {
                     this.UT.remove(var3);
                  } else {
                     this.UT.set(var3, (ICExpression)var2);
                  }

                  return true;
               }
            }
         }

         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      if (!var1.getSourceCustomizer().generateOperation(this, var1)) {
         COperatorType var2 = this.pC.getType();
         if (var2 == COperatorType.CAST) {
            if (var1.getHideCasts()) {
               this.A.generate(var1);
            } else {
               boolean var3 = false;
               if (var3) {
                  var1.paren();
               }

               var1.paren();
               this.pC.getCastType().generate(var1);
               var1.parenClose();
               if (this.A instanceof ICExpression && !(this.A instanceof ICIdentifier)) {
                  ;
               }

               boolean var4 = this.pC(this.A, COperatorType.Associativity.RIGHT);
               if (var4) {
                  var1.paren();
               }

               this.A.generate(var1);
               if (var4) {
                  var1.parenClose();
               }

               if (var3) {
                  var1.parenClose();
               }
            }
         } else if (var2 == COperatorType.COND) {
            this.A.generate(var1);
            var1.space();
            var1.append("?");
            var1.space();
            this.kS.generate(var1);
            var1.append(":");
            var1.space();
            this.wS.generate(var1);
         } else if (var2 == COperatorType.SIZEOF) {
            var1.append(this.pC.toString());
            var1.paren();
            this.A.generate(var1);
            var1.parenClose();
         } else if (var2 == COperatorType.CUSTOM) {
            var1.append(this.pC.toString());
            var1.paren();
            int var9 = 0;

            for (ICExpression var5 : this.getOperands()) {
               if (var9 > 0) {
                  var1.append(", ");
               }

               var5.generate(var1);
               var9++;
            }

            var1.parenClose();
         } else {
            if (this.wS != null) {
               throw new RuntimeException("Expected a unary or duary operator at this point");
            }

            ICExpression var10 = this.A;
            ICExpression var12 = this.kS;
            if (this.pC.isUnary()) {
               if (this.kS != null) {
                  throw new RuntimeException("Unary operator operation does not accept more than one operand");
               }

               var12 = var10;
               var10 = null;
            }

            if (var10 != null) {
               boolean var13 = this.pC(var10, COperatorType.Associativity.LEFT);
               if (var13) {
                  var1.paren();
               }

               var10.generate(var1);
               if (var13) {
                  var1.parenClose();
               }
            }

            String var14 = this.pC.toString();
            if (var14.length() > 0) {
               if (this.pC.isUnary()) {
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
               var6 = this.pC(var12, COperatorType.Associativity.RIGHT);
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

      this.A(var1);
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private boolean pC(ICExpression var1, COperatorType.Associativity var2) {
      if (var1 instanceof ICPredicate) {
         var1 = ((ICPredicate)var1).getExpression();
      }

      ICOperator var3 = var1 instanceof ICOperation ? ((aex)var1).getOperator() : null;
      if (var3 == null) {
         return false;
      } else {
         try {
            COperatorType var4 = this.pC.getType();
            COperatorType var5 = var3.getType();
            if (!var4.equals(COperatorType.AND)
               && !var4.equals(COperatorType.XOR)
               && !var4.equals(COperatorType.OR)
               && !var4.equals(COperatorType.SHL)
               && !var4.equals(COperatorType.SHR)
               && !var4.equals(COperatorType.USHR)
               && !var4.equals(COperatorType.LOG_AND)
               && !var4.equals(COperatorType.LOG_OR)) {
               int var6 = this.pC.getPrecedenceDelta(var3);
               return var6 < 0 || var6 == 0 && this.pC.getAssociativity() != var2;
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
      ICOperator var2 = this.pC.getReverse();
      if (var2 == null) {
         return false;
      } else {
         this.pC = var2;
         return true;
      }
   }

   @Override
   public boolean mirror(ICOperatorFactory var1) {
      if (this.getCountOfOperands() != 2) {
         return false;
      } else {
         ICOperator var2 = this.pC.getMirror();
         if (var2 == null) {
            return false;
         } else {
            this.pC = var2;
            ICExpression var3 = this.A;
            this.A = this.kS;
            this.kS = var3;
            return true;
         }
      }
   }

   @Override
   public String toString() {
      if (this.getOperator().isCustom()) {
         StringBuilder var1 = new StringBuilder();
         var1.append(this.pC.toString()).append("(");
         byte var2 = 0;

         for (ICExpression var4 : this.getOperands()) {
            if (var2 > 0) {
               var1.append(", ");
            }

            var1.append(var4);
         }

         var1.append(")");
         return var1.toString();
      } else if (this.A == null) {
         return this.pC.toString();
      } else if (this.kS == null) {
         return this.pC + " " + this.A;
      } else {
         return this.wS == null ? "(" + this.A + " " + this.pC + " " + this.kS + ")" : this.A + " " + this.pC + " " + this.kS + this.pC + " " + this.wS;
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      return 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
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
         aex var2 = (aex)var1;
         if (this.pC == null) {
            if (var2.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var2.pC)) {
            return false;
         }

         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         if (this.kS == null) {
            if (var2.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var2.kS)) {
            return false;
         }

         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
            return false;
         }

         if (this.UT == null) {
            if (var2.UT != null) {
               return false;
            }
         } else if (!this.UT.equals(var2.UT)) {
            return false;
         }

         return true;
      }
   }

   public aex A() {
      aex var1 = new aex();
      var1.pC = this.pC;
      var1.A = this.A.duplicate();
      var1.kS = this.kS == null ? null : this.kS.duplicate();
      var1.wS = this.wS == null ? null : this.wS.duplicate();
      ArrayList var2 = null;
      if (this.UT != null) {
         var2 = new ArrayList(this.UT.size());

         for (ICExpression var4 : this.UT) {
            var2.add(var4.duplicate());
         }
      }

      var1.UT = var2;
      super.pC(var1);
      return var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      Long var3 = null;
      switch (this.pC.getType()) {
         case EQ:
            var3 = this.A.evaluate(var1, var2).equals(this.kS.evaluate(var1, var2)) ? 1L : 0L;
            break;
         case NE:
            var3 = this.A.evaluate(var1, var2).equals(this.kS.evaluate(var1, var2)) ? 0L : 1L;
            break;
         case GE:
            var3 = this.A.evaluate(var1, var2) >= this.kS.evaluate(var1, var2) ? 1L : 0L;
            break;
         case GT:
            var3 = this.A.evaluate(var1, var2) > this.kS.evaluate(var1, var2) ? 1L : 0L;
            break;
         case LE:
            var3 = this.A.evaluate(var1, var2) <= this.kS.evaluate(var1, var2) ? 1L : 0L;
            break;
         case LT:
            var3 = this.A.evaluate(var1, var2) < this.kS.evaluate(var1, var2) ? 1L : 0L;
            break;
         case ADD:
            var3 = this.A.evaluate(var1, var2) + this.kS.evaluate(var1, var2);
            break;
         case AND:
            var3 = this.A.evaluate(var1, var2) & this.kS.evaluate(var1, var2);
            break;
         case CAST:
            int var4 = var2.getTypeSize(this.pC.getCastType());
            long var5 = MathUtil.makeMask(var4 * 8);
            var3 = this.A.evaluate(var1, var2) & var5;
            break;
         case COND:
            var3 = this.A.evaluate(var1, var2) != 0L ? this.kS.evaluate(var1, var2) : this.wS.evaluate(var1, var2);
         case CUSTOM:
         case SIZEOF:
         default:
            break;
         case DIV:
            var3 = this.A.evaluate(var1, var2) / this.kS.evaluate(var1, var2);
            break;
         case LOG_AND:
            var3 = this.A.evaluate(var1, var2) != 0L && this.kS.evaluate(var1, var2) != 0L ? 1L : 0L;
            break;
         case LOG_IDENT:
            var3 = this.A.evaluate(var1, var2);
            break;
         case LOG_NOT:
            var3 = this.A.evaluate(var1, var2) != 0L ? 0L : 1L;
            break;
         case LOG_OR:
            var3 = this.A.evaluate(var1, var2) == 0L && this.kS.evaluate(var1, var2) == 0L ? 0L : 1L;
            break;
         case MUL:
            var3 = this.A.evaluate(var1, var2) * this.kS.evaluate(var1, var2);
            break;
         case NEG:
            var3 = -this.A.evaluate(var1, var2);
            break;
         case NOT:
            var3 = ~this.A.evaluate(var1, var2);
            break;
         case OR:
            var3 = this.A.evaluate(var1, var2) | this.kS.evaluate(var1, var2);
            break;
         case PTR:
            if (this.A instanceof aeo) {
               var3 = var2.readMemory(this.A.evaluate(var1, var2), var2.getBaseTypeSize(((aeo)this.A).getType()));
            } else {
               if (!(this.A instanceof aex)) {
                  throw new CSimulationException(Strings.ff("PTR invalid (%s)", this.A));
               }

               ICIdentifier var7 = CMethodSimulatorUtils.getBasePointer((aex)this.A);
               if (var7 != null) {
                  var3 = var2.readMemory(this.A.evaluate(var1, var2), var2.getBaseTypeSize(var7.getType()));
               } else {
                  if (var2.getDefaultPointedSize() == null) {
                     throw new CSimulationException("cant find size to read for PTR operation");
                  }

                  var3 = var2.readMemory(this.A.evaluate(var1, var2), var2.getDefaultPointedSize());
               }
            }
            break;
         case REF:
            if (!(this.A instanceof aeo)) {
               throw new CSimulationException(Strings.ff("REF on non id (%s)", this.A));
            }

            var3 = var2.getVarAddress((aeo)this.A);
            break;
         case REM:
            var3 = this.A.evaluate(var1, var2) % this.kS.evaluate(var1, var2);
            break;
         case SHL:
            var3 = this.A.evaluate(var1, var2) << (int)this.kS.evaluate(var1, var2).longValue();
            break;
         case SHR:
            var3 = this.A.evaluate(var1, var2) >> (int)this.kS.evaluate(var1, var2).longValue();
            break;
         case SUB:
            var3 = this.A.evaluate(var1, var2) - this.kS.evaluate(var1, var2);
            break;
         case USHR:
            var3 = this.A.evaluate(var1, var2) >>> (int)this.kS.evaluate(var1, var2).longValue();
            break;
         case XOR:
            var3 = this.A.evaluate(var1, var2) ^ this.kS.evaluate(var1, var2);
      }

      if (var3 == null) {
         throw new CSimulationException(Strings.ff("TBI: operator (%s)", this.pC));
      } else {
         return var3;
      }
   }
}
