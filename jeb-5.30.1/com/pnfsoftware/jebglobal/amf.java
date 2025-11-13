package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstant;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTypeFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.FunctionOptype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Ser
public class amf extends ane implements IEOperation {
   private static final ILogger q = GlobalLog.getLogger(amf.class);
   @SerId(1)
   private OperationType RF;
   @SerId(2)
   private int xK;
   @SerId(3)
   private IEGeneric Dw;
   @SerId(4)
   private IEGeneric Uv;
   @SerId(5)
   private IEGeneric[] oW;
   @SerId(6)
   private FunctionOptype gO;

   public static amf q(OperationType var0, IEGeneric... var1) {
      if (var1.length == 1) {
         return new amf(var0, var1[0]);
      } else if (var1.length == 2) {
         return new amf(var0, var1[0], var1[1]);
      } else {
         throw new RuntimeException();
      }
   }

   public static IEOperation q(IEGeneric var0, int var1, boolean var2) {
      int var3 = var0.getBitsize();
      OperationType var4 = var2 && var1 > var3 ? OperationType.CAST_S : OperationType.CAST;
      return new amf(var4, var1, var0, null);
   }

   public static IEOperation q(OperationType var0, IEGeneric var1, int var2) {
      if (var0 != null && var0.isConversion()) {
         return new amf(var0, var2, var1, null);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public amf(OperationType var1, IEGeneric var2) {
      this(var1, var2, null);
   }

   public amf(OperationType var1, IEGeneric var2, IEGeneric var3) {
      if (var1 == null) {
         throw new NullPointerException("Null operator");
      } else if (var1 == OperationType.FUNCTION) {
         throw new NullPointerException("Custom operations (functions) cannot be created with this constructor");
      } else if (var2 == null) {
         throw new NullPointerException("Null first operand");
      } else {
         this.RF = var1;
         this.Dw = var2;
         this.Uv = var3;
         this.xK = this.xK();
         this.verify();
      }
   }

   public amf(FunctionOptype var1, IEGeneric... var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.gO = var1;
         this.RF = OperationType.FUNCTION;
         var1.validateOperands(var2);
         if (var2.length >= 1) {
            this.Dw = var2[0];
         }

         if (var2.length >= 2) {
            this.Uv = var2[1];
         }

         if (var2.length >= 3) {
            this.oW = (IEGeneric[])Arrays.copyOfRange(var2, 2, var2.length);
         }

         this.xK = var1.getResultBitsize(var2);
      }
   }

   private amf(OperationType var1, int var2, IEGeneric var3, IEGeneric var4) {
      this.RF = var1;
      this.xK = var2;
      this.Dw = var3;
      this.Uv = var4;
   }

   private amf(FunctionOptype var1, int var2, IEGeneric var3, IEGeneric var4, IEGeneric... var5) {
      this.RF = OperationType.FUNCTION;
      this.xK = var2;
      this.Dw = var3;
      this.Uv = var4;
      this.gO = var1;
      this.oW = var5;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private int xK() {
      switch (this.RF) {
         case LOG_AND:
         case LOG_OR:
         case LOG_NOT:
         case LOG_EQ:
         case LOG_NEQ:
         case GT_S:
         case GE_S:
         case LT_S:
         case LE_S:
         case GT_U:
         case GE_U:
         case LT_U:
         case LE_U:
         case PAR:
         case CARRY:
         case FEQ:
         case FNE:
         case FLT:
         case FGT:
         case FLE:
         case FGE:
         case FUN:
            return 1;
         case ADD:
         case SUB:
         case MUL:
         case DIV_S:
         case DIV_U:
         case REM_S:
         case REM_U:
         case AND:
         case OR:
         case XOR:
         case ROL:
         case ROR:
         case SAR:
         case SHL:
         case SHR:
         case NOT:
         case POW:
         case ADD_SSAT:
         case ADD_USAT:
         case SUB_SSAT:
         case SUB_USAT:
            return this.Dw.getBitsize();
         case MUL2_S:
         case MUL2_U:
         case DIV2_S:
         case DIV2_U:
            return 2 * this.Dw.getBitsize();
         case FADD:
         case FSUB:
         case FMUL:
         case FDIV:
            return this.Dw.getBitsize();
         case CAST:
         case CAST_S:
         case FP2FP:
         case FP2INT:
         case INT2FP:
         case FP2UINT:
         case UINT2FP:
            return -1;
         case FUNCTION:
            return -1;
         default:
            throw new RuntimeException("No bitsize for IR operator: " + this.RF);
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public void verify() throws IllegalIntermediateExpressionException {
      if (!this.isCustomOperation()) {
         int var1 = this.Uv != null ? 2 : 1;
         if (!this.RF.isValid(var1)) {
            throw new IllegalIntermediateExpressionException("Invalid number of operands for operation type %s (got %d)", this.RF, var1);
         } else if (!this.RF.isConversion()) {
            switch (this.RF) {
               case LOG_AND:
               case LOG_OR:
               case LOG_NOT:
               case PAR:
               case ROL:
               case ROR:
               case SAR:
               case SHL:
               case SHR:
               case NOT:
                  break;
               case LOG_EQ:
               case LOG_NEQ:
               case GT_S:
               case GE_S:
               case LT_S:
               case LE_S:
               case GT_U:
               case GE_U:
               case LT_U:
               case LE_U:
               case CARRY:
               case FEQ:
               case FNE:
               case FLT:
               case FGT:
               case FLE:
               case FGE:
               case FUN:
               case ADD:
               case SUB:
               case MUL:
               case DIV_S:
               case DIV_U:
               case REM_S:
               case REM_U:
               case AND:
               case OR:
               case XOR:
               case POW:
               case ADD_SSAT:
               case ADD_USAT:
               case SUB_SSAT:
               case SUB_USAT:
               case MUL2_S:
               case MUL2_U:
               case FADD:
               case FSUB:
               case FMUL:
               case FDIV:
                  if (this.Dw.getBitsize() != this.Uv.getBitsize()) {
                     throw new IllegalIntermediateExpressionException(
                        "The operands op1 and op2 do not have the same bitsize: %d, %d (operator: %s)", this.Dw.getBitsize(), this.Uv.getBitsize(), this.RF
                     );
                  }
                  break;
               case DIV2_S:
               case DIV2_U:
                  if (this.Dw.getBitsize() != 2 * this.Uv.getBitsize()) {
                     throw new IllegalIntermediateExpressionException("Unexpected bitsizes for %s", this.RF);
                  }
                  break;
               default:
                  throw new RuntimeException("TBI");
            }

            int var2 = this.xK();
            if (this.xK != var2) {
               throw new IllegalIntermediateExpressionException("Illegal bitsize: currently %d, expected to be %d", this.xK, var2);
            }
         }
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      var1 = 31 * var1 + this.xK;
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      var1 = 31 * var1 + (this.Uv == null ? 0 : this.Uv.hashCode());
      var1 = 31 * var1 + (this.oW == null ? 0 : Arrays.hashCode((Object[])this.oW));
      return 31 * var1 + (this.gO == null ? 0 : this.gO.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         amf var3 = (amf)var1;
         if (this.RF != var3.RF) {
            return false;
         } else if (this.xK != var3.xK) {
            return false;
         } else if (this.gO != var3.gO) {
            return false;
         } else {
            if (this.Dw == null) {
               if (var3.Dw != null) {
                  return false;
               }
            } else if (!this.Dw.equalsEx(var3.Dw, var2)) {
               return false;
            }

            if (this.Uv == null) {
               if (var3.Uv != null) {
                  return false;
               }
            } else if (!this.Uv.equalsEx(var3.Uv, var2)) {
               return false;
            }

            if (this.oW == null) {
               if (var3.oW != null) {
                  return false;
               }
            } else {
               if (var3.oW == null) {
                  return false;
               }

               if (var3.oW.length != this.oW.length) {
                  return false;
               }

               for (int var4 = 0; var4 < this.oW.length; var4++) {
                  if (!this.oW[var4].equalsEx(var3.oW[var4], var2)) {
                     return false;
                  }
               }
            }

            return true;
         }
      }
   }

   @Override
   public OperationType getOperationType() {
      return this.RF;
   }

   public void q(OperationType var1) {
      this.RF = var1;
   }

   public boolean RF(OperationType var1) {
      if (var1 == OperationType.FUNCTION) {
         return false;
      } else {
         int var2 = var1.getOperandCount();
         if (var2 == 1) {
            this.Uv = null;
         } else if (var2 != 2) {
            return false;
         }

         this.oW = null;
         this.RF = var1;
         return true;
      }
   }

   @Override
   public FunctionOptype getCustomOperationType() {
      return this.gO;
   }

   @Override
   public IEGeneric getOperand1() {
      return this.Dw;
   }

   @Override
   public IEGeneric getOperand2() {
      return this.Uv;
   }

   @Override
   public IEGeneric getOperand(int var1) {
      if (var1 < 0) {
         return null;
      } else if (var1 == 0) {
         return this.Dw;
      } else if (var1 == 1) {
         return this.Uv;
      } else {
         int var2 = var1 - 2;
         return var2 >= 0 && this.oW != null && var2 < this.oW.length ? this.oW[var2] : null;
      }
   }

   @Override
   public List getOperands() {
      if (this.isCustomOperation()) {
         ArrayList var1 = new ArrayList(this.getCountOfOperands());
         if (this.Dw != null) {
            var1.add(this.Dw);
         }

         if (this.Uv != null) {
            var1.add(this.Uv);
         }

         if (this.oW != null) {
            var1.addAll(Arrays.asList(this.oW));
         }

         return var1;
      } else {
         return this.Uv == null ? Arrays.asList(this.Dw) : Arrays.asList(this.Dw, this.Uv);
      }
   }

   @Override
   public String getOperationName() {
      return this.isCustomOperation() ? this.gO.getName() : this.RF.toString();
   }

   @Override
   public int getCountOfOperands() {
      if (this.isCustomOperation()) {
         int var1 = 0;
         if (this.Dw != null) {
            var1++;
         }

         if (this.Uv != null) {
            var1++;
         }

         if (this.oW != null) {
            var1 += this.oW.length;
         }

         return var1;
      } else {
         return this.Uv == null ? 1 : 2;
      }
   }

   @Override
   public int getBitsize() {
      return this.xK;
   }

   @Override
   public int getPriority() {
      return 10;
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      if (this.Dw != null) {
         this.Dw.getUsed(var1);
      }

      if (this.Uv != null) {
         this.Uv.getUsed(var1);
      }

      if (this.oW != null) {
         for (IEGeneric var5 : this.oW) {
            var5.getUsed(var1);
         }
      }
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
   }

   @Override
   public boolean accessesMemory() {
      if (this.Dw != null && this.Dw.accessesMemory()) {
         return true;
      } else if (this.Uv != null && this.Uv.accessesMemory()) {
         return true;
      } else {
         if (this.oW != null) {
            for (IEGeneric var4 : this.oW) {
               if (var4.accessesMemory()) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private void q(IEGeneric var1, IEGeneric var2) {
      if (!this.RF.isAnyOf(OperationType.LOG_OR, OperationType.LOG_AND, OperationType.LOG_NOT)) {
         RF(var1, var2);
      }
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      int var3 = 0;
      if (this.Dw != null) {
         if (this.Dw == var1) {
            this.q(this.Dw, var2);
            this.Dw = var2.duplicate();
            var3++;
         } else {
            var3 += this.Dw.replaceVar(var1, var2);
         }
      }

      if (this.Uv != null) {
         if (this.Uv == var1) {
            this.q(this.Uv, var2);
            this.Uv = var2.duplicate();
            var3++;
         } else {
            var3 += this.Uv.replaceVar(var1, var2);
         }
      }

      if (this.oW != null) {
         for (int var4 = 0; var4 < this.oW.length; var4++) {
            if (this.oW[var4] == var1) {
               this.q(this.oW[var4], var2);
               this.oW[var4] = var2.duplicate();
               var3++;
            } else {
               var3 += this.oW[var4].replaceVar(var1, var2);
            }
         }
      }

      return var3;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      CollectionUtil.addNonNulls(var1, this.Dw, this.Uv);
      CollectionUtil.addNonNulls(var1, this.oW);
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      q(var1, var2);
      if (this.Dw == var1) {
         this.q(this.Dw, var2);
         this.Dw = var2;
         return true;
      } else if (this.Uv == var1) {
         if (this.RF.isShift()) {
            q(var2);
         } else {
            this.q(this.Uv, var2);
         }

         this.Uv = var2;
         return true;
      } else {
         if (this.oW != null) {
            for (int var3 = 0; var3 < this.oW.length; var3++) {
               if (this.oW[var3] == var1) {
                  this.q(this.oW[var3], var2);
                  this.oW[var3] = var2;
                  return true;
               }
            }
         }

         return false;
      }
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      IWildcardTypeManager var2 = var1.getWildcardTypeManager();
      if (this.Dw != null) {
         this.Dw.updateTypes(var1);
      }

      if (this.Uv != null) {
         this.Uv.updateTypes(var1);
      }

      if (this.oW != null) {
         for (IEGeneric var6 : this.oW) {
            var6.updateTypes(var1);
         }
      }

      if (!this.isCustomOperation()) {
         if (this.RF == OperationType.ADD && this.Dw.getType() != null && this.Dw.getType().isDefined() && this.Dw.getType().isPointer()) {
            IReferenceType var9 = (IReferenceType)TypeUtil.getNonAlias(this.Dw.getType().getNativeType(), IReferenceType.class);
            INativeType var11 = var9.getPointedType();
            if (var11.getSize() != 0 && this.Uv.isImm() && this.Uv.asImm().canReadAsLong() && this.Uv.asImm().getValueAsLong() % var11.getSize() == 0L) {
               this.setType(var2.create(var9), var1);
               return;
            }
         }

         IWildcardType.Group var10 = this.Dw.getType() == null ? null : this.Dw.getType().getGroup();
         IWildcardType.Group var12 = null;
         if (this.Uv != null) {
            var12 = this.Uv.getType() == null ? null : this.Uv.getType().getGroup();
         }

         IWildcardType var13 = this.safelyType(var2);
         if (!var13.isDefined() && var13.getGroup() == null) {
            switch (this.RF) {
               case ADD:
                  if (var10 != IWildcardType.Group.UINT || var12 != IWildcardType.Group.UINT && var12 != null) {
                     if (var10 != IWildcardType.Group.INTEGER || var12 != IWildcardType.Group.INTEGER && var12 != null) {
                        if ((var10 != IWildcardType.Group.INTEGER || var12 != IWildcardType.Group.INTEGER)
                           && (var10 != IWildcardType.Group.POINTER || var12 != IWildcardType.Group.POINTER)) {
                           if (var10 == IWildcardType.Group.INTEGER && var12 == IWildcardType.Group.POINTER
                              || var10 == IWildcardType.Group.POINTER && var12 == IWildcardType.Group.INTEGER) {
                              this.setType(var13.updateGroup(IWildcardType.Group.POINTER), var1);
                           }
                        } else {
                           this.setType(var13.updateGroup(IWildcardType.Group.INTEGER), var1);
                        }
                     } else {
                        this.setType(var13.updateGroup(IWildcardType.Group.INTEGER), var1);
                     }
                  } else {
                     this.setType(var13.updateGroup(IWildcardType.Group.UINT), var1);
                  }
                  break;
               case SUB:
                  if (var10 == IWildcardType.Group.UINT && var12 == IWildcardType.Group.UINT) {
                     this.setType(var13.updateGroup(IWildcardType.Group.UINT), var1);
                  } else if (var10 == IWildcardType.Group.INTEGER || var12 == IWildcardType.Group.POINTER) {
                     this.setType(var13.updateGroup(IWildcardType.Group.INTEGER), var1);
                  }
                  break;
               case MUL:
               case DIV_S:
               case DIV_U:
               case REM_S:
               case REM_U:
               case XOR:
               case ROL:
               case ROR:
               case SAR:
               case SHL:
               case SHR:
                  if (var10 != IWildcardType.Group.UINT || var12 != IWildcardType.Group.UINT && var12 != null) {
                     if (var10 == IWildcardType.Group.INTEGER && (var12 == IWildcardType.Group.INTEGER || var12 == null)) {
                        this.setType(var13.updateGroup(IWildcardType.Group.INTEGER), var1);
                     }
                  } else {
                     this.setType(var13.updateGroup(IWildcardType.Group.UINT), var1);
                  }
               case AND:
               case OR:
               case POW:
               case ADD_SSAT:
               case ADD_USAT:
               case SUB_SSAT:
               case SUB_USAT:
               case MUL2_S:
               case MUL2_U:
               case DIV2_S:
               case DIV2_U:
               case CAST:
               case CAST_S:
               case FP2FP:
               case FP2INT:
               case FP2UINT:
               default:
                  break;
               case NOT:
                  if (var10 == IWildcardType.Group.UINT) {
                     this.setType(var13.updateGroup(IWildcardType.Group.UINT), var1);
                  } else if (var10 == IWildcardType.Group.INTEGER) {
                     this.setType(var13.updateGroup(IWildcardType.Group.INTEGER), var1);
                  }
                  break;
               case FADD:
               case FSUB:
               case FMUL:
               case FDIV:
               case INT2FP:
               case UINT2FP:
                  this.setType(var13.updateGroup(IWildcardType.Group.FLOAT), var1);
            }
         }

         IWildcardType.Group var14 = null;
         switch (this.RF) {
            case FEQ:
            case FNE:
            case FLT:
            case FGT:
            case FLE:
            case FGE:
            case FADD:
            case FSUB:
            case FMUL:
            case FDIV:
            case FP2FP:
            case FP2INT:
            case FP2UINT:
               this.Dw = this.RF(this.Dw);
               IWildcardType var15 = this.Dw.safelyType(var2);
               if (var15.isUpdatable()) {
                  this.Dw.setType(var15.updateGroup(IWildcardType.Group.FLOAT));
               }

               if (this.Uv != null) {
                  this.Uv = this.RF(this.Uv);
                  IWildcardType var18 = this.Uv.safelyType(var2);
                  if (var18.isUpdatable()) {
                     this.Uv.setType(var18.updateGroup(IWildcardType.Group.FLOAT));
                  }
               }
            case FUN:
            case ADD:
            case SUB:
            case MUL:
            case AND:
            case OR:
            case XOR:
            case ROL:
            case ROR:
            case SAR:
            case SHL:
            case SHR:
            case NOT:
            case POW:
            case ADD_SSAT:
            case ADD_USAT:
            case SUB_SSAT:
            case SUB_USAT:
            case MUL2_S:
            case MUL2_U:
            case DIV2_S:
            case DIV2_U:
            case CAST:
            case CAST_S:
            case INT2FP:
            default:
               break;
            case DIV_U:
            case REM_U:
               var14 = IWildcardType.Group.UINT;
            case DIV_S:
            case REM_S:
               if (var14 == null) {
                  var14 = IWildcardType.Group.INTEGER;
               }

               this.Dw = this.RF(this.Dw);
               IWildcardType var7 = this.Dw.safelyType(var2);
               if (!var7.isDefined() && var7.getGroup() == null) {
                  this.Dw.setType(var7.updateGroup(var14), var1);
               }

               this.Uv = this.RF(this.Uv);
               IWildcardType var8 = this.Uv.safelyType(var2);
               if (!var8.isDefined() && var8.getGroup() == null) {
                  this.Uv.setType(var8.updateGroup(var14), var1);
               }
         }

         if (this.RF == OperationType.ADD || this.RF == OperationType.SUB) {
            if (var13.isWildcardPointer() && var13.getPointedGroup() != null) {
               IWildcardType var16 = this.Dw.getType();
               if (var16 != null && var16.isWildcardPointer() && var16.getPointedGroup() == null) {
                  this.Dw.setType(var16.updatePointedGroup(var13.getPointedGroup()), var1);
               } else {
                  IWildcardType var19 = this.Uv.getType();
                  if (var19 != null && var19.isWildcardPointer() && var19.getPointedGroup() == null) {
                     this.Uv.setType(var19.updatePointedGroup(var13.getPointedGroup()), var1);
                  }
               }
            }

            if (var13.isWildcardPointer() && var13.getPointedGroup() == null) {
               IWildcardType var17 = this.Dw.getType();
               if (var17 != null && var17.isWildcardPointer() && var17.getPointedGroup() != null) {
                  this.setType(var13.updatePointedGroup(var17.getPointedGroup()), var1);
               } else {
                  IWildcardType var20 = this.Uv.getType();
                  if (var20 != null && var20.isWildcardPointer() && var20.getPointedGroup() != null) {
                     this.setType(var13.updatePointedGroup(var20.getPointedGroup()), var1);
                  }
               }
            }
         }

         if (this.Dw != null
            && this.Uv != null
            && (this.Dw.getSafeType(var2).isPointer() || this.Uv.getSafeType(var2).isPointer())
            && !this.getSafeType(var2).isPointer()) {
            this.setType(var2.createPointer(0), var1);
         }
      }
   }

   private IEGeneric RF(IEGeneric var1) {
      return (IEGeneric)(var1 instanceof IEImm && !((IEImm)var1).isMutable() ? ((IEImm)var1).duplicateToMutable() : var1);
   }

   public amf q() {
      IEGeneric var1 = this.Dw == null ? null : this.Dw.duplicate();
      IEGeneric var2 = this.Uv == null ? null : this.Uv.duplicate();
      IEGeneric[] var3 = null;
      if (this.oW != null) {
         var3 = new IEGeneric[this.oW.length];

         for (int var4 = 0; var4 < this.oW.length; var4++) {
            var3[var4] = this.oW[var4].duplicate();
         }
      }

      amf var5;
      if (this.isCustomOperation()) {
         var5 = new amf(this.gO, this.xK, var1, var2, var3);
      } else {
         var5 = new amf(this.RF, this.xK, var1, var2);
      }

      return (amf)this.q(var5);
   }

   @Override
   public IEImm evaluate(EState var1) {
      if (this.isCustomOperation()) {
         throw new EvaluationException("Cannot evaluate custom function: " + this.gO);
      } else if (!this.RF.isValid(this.Uv == null ? 1 : 2)) {
         throw new RuntimeException(Strings.ff("Invalid operation type \"%s\" for parameters (%s, %s)", this.RF, this.Dw, Strings.safe(this.Uv)));
      } else {
         switch (this.RF) {
            case AND:
               if (this.Dw instanceof IEImm && ((IEImm)this.Dw).isZero()) {
                  return EUtil.zero(this.Dw.getBitsize());
               }

               if (this.Uv instanceof IEImm && ((IEImm)this.Uv).isZero()) {
                  return EUtil.zero(this.Dw.getBitsize());
               }
               break;
            case XOR:
               if (this.Dw.equals(this.Uv)) {
                  return EUtil.zero(this.Dw.getBitsize());
               }
         }

         IEImm var2 = null;
         if (this.Dw != null) {
            var2 = this.Dw.evaluate(var1);
            if (var2 == null) {
               return null;
            }
         }

         IEImm var3 = null;
         if (this.Uv != null) {
            var3 = this.Uv.evaluate(var1);
            if (var3 == null) {
               return null;
            }
         }

         if (this.isCustomOperation()) {
            ArrayList var22 = new ArrayList();
            if (var2 != null) {
               var22.add(var2);
            }

            if (var3 != null) {
               var22.add(var3);
            }

            if (this.oW != null) {
               for (IEGeneric var38 : this.oW) {
                  IEImm var9 = var38.evaluate(var1);
                  if (var9 == null) {
                     return null;
                  }

                  var22.add(var9);
               }
            }

            throw new RuntimeException();
         } else {
            switch (this.RF) {
               case LOG_AND:
                  return var2._signum() != 0 && var3._signum() != 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case LOG_OR:
                  return var2._signum() == 0 && var3._signum() == 0 ? alu.q(0L, 1) : alu.q(1L, 1);
               case LOG_NOT:
                  return var2._signum() != 0 ? alu.q(0L, 1) : alu.q(1L, 1);
               case LOG_EQ:
                  return var2._cmp(var3) == 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case LOG_NEQ:
                  return var2._cmp(var3) != 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case GT_S:
                  return var2._cmp(var3) > 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case GE_S:
                  return var2._cmp(var3) >= 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case LT_S:
                  return var2._cmp(var3) < 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case LE_S:
                  return var2._cmp(var3) <= 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case GT_U:
                  return var2._cmpU(var3) > 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case GE_U:
                  return var2._cmpU(var3) >= 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case LT_U:
                  return var2._cmpU(var3) < 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case LE_U:
                  return var2._cmpU(var3) <= 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case PAR:
                  IEImm var21 = var2;

                  int var27;
                  for (var27 = 0; var21._signum() != 0; var21 = var21._shr(1)) {
                     if (var21._testbit(0)) {
                        var27++;
                     }
                  }

                  return var27 % 2 == 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case CARRY:
                  IEImm var20 = var2._add(var3);
                  IEImm var26 = var2._xor(var3)._xor(var20);
                  IEImm var35 = var2._xor(var20);
                  IEImm var7 = var2._xor(var3)._not();
                  IEImm var8 = var26._xor(var35._and(var7));
                  return var8._testbit(var2.getBitsize() - 1) ? alu.q(1L, 1) : alu.q(0L, 1);
               case FEQ:
                  Integer var19 = var2._fcmp(var3);
                  return var19 != null && var19 == 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case FNE:
                  Integer var18 = var2._fcmp(var3);
                  return var18 != null && var18 != 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case FLT:
                  Integer var17 = var2._fcmp(var3);
                  return var17 != null && var17 < 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case FGT:
                  Integer var16 = var2._fcmp(var3);
                  return var16 != null && var16 > 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case FLE:
                  Integer var15 = var2._fcmp(var3);
                  return var15 != null && var15 <= 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case FGE:
                  Integer var14 = var2._fcmp(var3);
                  return var14 != null && var14 >= 0 ? alu.q(1L, 1) : alu.q(0L, 1);
               case FUN:
                  return var2._fcmp(var2) == null ? alu.q(1L, 1) : alu.q(0L, 1);
               case ADD:
                  var2 = var2._add(var3);
                  break;
               case SUB:
                  var2 = var2._sub(var3);
                  break;
               case MUL:
                  var2 = var2._mul(var3);
                  break;
               case DIV_S:
                  if (var3._signum() == 0) {
                     if (var1.isSoftFailMode()) {
                        return null;
                     }

                     throw new EvaluationException("Signed division by 0");
                  }

                  var2 = var2._div(var3);
                  break;
               case DIV_U:
                  if (var3._signum() == 0) {
                     if (var1.isSoftFailMode()) {
                        return null;
                     }

                     throw new EvaluationException("Unsigned division by 0");
                  }

                  var2 = var2._divU(var3);
                  break;
               case REM_S:
                  if (var3._signum() == 0) {
                     if (var1.isSoftFailMode()) {
                        return null;
                     }

                     throw new EvaluationException("Signed modulo by 0");
                  }

                  var2 = var2._rem(var3);
                  break;
               case REM_U:
                  if (var3._signum() == 0) {
                     if (var1.isSoftFailMode()) {
                        return null;
                     }

                     throw new EvaluationException("Unsigned modulo by 0");
                  }

                  var2 = var2._remU(var3);
                  break;
               case AND:
                  var2 = var2._and(var3);
                  break;
               case OR:
                  var2 = var2._or(var3);
                  break;
               case XOR:
                  var2 = var2._xor(var3);
                  break;
               case ROL:
                  var2 = var2._rol(EUtil.evalAsSaturatedPositiveInt(var3));
                  break;
               case ROR:
                  var2 = var2._ror(EUtil.evalAsSaturatedPositiveInt(var3));
                  break;
               case SAR:
                  var2 = var2._sar(EUtil.evalAsSaturatedPositiveInt(var3));
                  break;
               case SHL:
                  var2 = var2._shl(EUtil.evalAsSaturatedPositiveInt(var3));
                  break;
               case SHR:
                  var2 = var2._shr(EUtil.evalAsSaturatedPositiveInt(var3));
                  break;
               case NOT:
                  var2 = var2._not();
                  break;
               case POW:
                  var2 = var2._pow(EUtil.evalAsSaturatedPositiveInt(var3));
                  break;
               case ADD_SSAT:
               case ADD_USAT:
               case SUB_SSAT:
               case SUB_USAT:
               case MUL2_S:
               case MUL2_U:
               case DIV2_S:
               case DIV2_U:
               default:
                  var2 = null;
                  break;
               case FADD:
                  var2 = var2._fadd(var3);
                  break;
               case FSUB:
                  var2 = var2._fsub(var3);
                  break;
               case FMUL:
                  var2 = var2._fmul(var3);
                  break;
               case FDIV:
                  var2 = var2._fdiv(var3);
                  break;
               case CAST:
                  return var2.expand(this.xK);
               case CAST_S:
                  return var2.truncate(this.xK);
               case FP2FP:
                  int var13 = this.Dw.getBitsize();
                  int var25 = this.getBitsize();
                  if (var13 == 32) {
                     if (var25 == 32) {
                        if (var2.getGroup() == IWildcardType.Group.FLOAT) {
                           return var2;
                        }

                        return alw.RF(var2.getValueAsSingleFloat());
                     }

                     if (var25 == 64) {
                        return alx.xK((double)var2.getValueAsSingleFloat());
                     }

                     if (var25 == 80) {
                        return aly.xK((double)var2.getValueAsSingleFloat());
                     }
                  } else if (var13 == 64) {
                     if (var25 == 32) {
                        return alw.RF((float)var2.getValueAsDoubleFloat());
                     }

                     if (var25 == 64) {
                        if (var2.getGroup() == IWildcardType.Group.FLOAT) {
                           return var2;
                        }

                        return alx.xK(var2.getValueAsDoubleFloat());
                     }

                     if (var25 == 80) {
                        return aly.xK(var2.getValueAsDoubleFloat());
                     }
                  } else if (var13 == 80) {
                     if (var25 == 32) {
                        return alw.RF((float)var2.getValueAsDoubleFloat());
                     }

                     if (var25 == 64) {
                        return alx.xK(var2.getValueAsDoubleFloat());
                     }

                     if (var25 == 80) {
                        if (var2.getGroup() == IWildcardType.Group.FLOAT) {
                           return var2;
                        }

                        return aly.xK(var2.getValueAsDoubleFloat());
                     }
                  }

                  var2 = null;
                  break;
               case FP2INT:
                  int var12 = this.Dw.getBitsize();
                  int var24 = this.getBitsize();
                  if (var12 == 32) {
                     float var33 = var2.getValueAsSingleFloat();
                     if (var24 == 32) {
                        if (!(var33 < -2.1474836E9F) && !(var33 > 2.1474836E9F)) {
                           return alu.q((int)var33, 32);
                        }

                        return null;
                     }

                     if (var24 == 64) {
                        if (!(var33 < -9.223372E18F) && !(var33 > 9.223372E18F)) {
                           return alu.q((long)var33, 64);
                        }

                        return null;
                     }
                  } else if (var12 == 64) {
                     double var34 = var2.getValueAsDoubleFloat();
                     if (var24 == 32) {
                        if (!(var34 < -2.1474836E9F) && !(var34 > 2.147483647E9)) {
                           return alu.q((int)var34, 32);
                        }

                        return null;
                     }

                     if (var24 == 64) {
                        if (!(var34 < -9.223372E18F) && !(var34 > 9.223372E18F)) {
                           return alu.q((long)var34, 64);
                        }

                        return null;
                     }
                  }

                  var2 = null;
                  break;
               case INT2FP:
               case UINT2FP:
                  int var11 = this.Dw.getBitsize();
                  int var23 = this.getBitsize();
                  BigInteger var30 = var2.getValue();
                  if (var11 == 32) {
                     var30 = var30.and(BigInteger.valueOf(-1L));
                     if (this.RF == OperationType.UINT2FP) {
                        var30 = new BigInteger(1, var30.toByteArray());
                     }

                     if (var23 == 32) {
                        return alw.RF(var30.floatValue());
                     }

                     if (var23 == 64) {
                        return alx.xK(var30.doubleValue());
                     }
                  } else if (var11 == 64) {
                     var30 = var30.and(BigInteger.valueOf(-1L));
                     if (this.RF == OperationType.UINT2FP) {
                        var30 = new BigInteger(1, var30.toByteArray());
                     }

                     if (var23 == 32) {
                        return alw.RF(var30.floatValue());
                     }

                     if (var23 == 64) {
                        return alx.xK(var30.doubleValue());
                     }
                  }

                  var2 = null;
                  break;
               case FP2UINT:
                  int var4 = this.Dw.getBitsize();
                  int var5 = this.getBitsize();
                  if (var4 == 32) {
                     float var6 = var2.getValueAsSingleFloat();
                     if (var5 == 32) {
                        if (!(var6 < 0.0F) && !(var6 > 4.2949673E9F)) {
                           return alu.q((long)var6, 32);
                        }

                        return null;
                     }

                     if (var5 == 64) {
                        if (!(var6 < 0.0F) && !(var6 > -1.0F)) {
                           return alu.q((long)var6, 64);
                        }

                        return null;
                     }
                  } else if (var4 == 64) {
                     double var29 = var2.getValueAsDoubleFloat();
                     if (var5 == 32) {
                        if (!(var29 < 0.0) && !(var29 > 4.294967295E9)) {
                           return alu.q((long)var29, 32);
                        }

                        return null;
                     }

                     if (var5 == 64) {
                        if (!(var29 < 0.0) && !(var29 > -1.0)) {
                           return alu.q((long)var29, 64);
                        }

                        return null;
                     }
                  }

                  var2 = null;
            }

            if (var2 == null) {
               if (var1.isSoftFailMode()) {
                  return null;
               } else {
                  throw new EvaluationException("Evaluation cannot be done");
               }
            } else {
               return var2;
            }
         }
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      return this.q(var1, var2, true);
   }

   public ICElement q(IERoutineContext var1, ICMethod var2, boolean var3) {
      IWildcardTypeManager var4 = var1.getWildcardTypeManager();
      if (var3) {
         IWildcardType var5 = this.getSafeType(var4);
         if (var5.isPointer() && var5.getPointedBitsize() != 0) {
            boolean var6 = false;
            if ((this.RF == OperationType.ADD || this.RF == OperationType.SUB)
               && this.Uv instanceof alu
               && this.Dw.getType() != null
               && this.Dw.getType().isPointer()) {
               var6 = true;
            }

            if (this.isCustomOperation()) {
               var6 = true;
            }

            if (!var6) {
               ant var7 = new ant(var1, var5.getPointedBitsize(), this, null);
               IEVar var8 = var7.q(var2);
               if (var8 != null) {
                  return var8.generateC(var1, var2);
               }
            }
         }
      }

      ICOperatorFactory var26 = var2.getOperatorFactory();
      ICTypeFactory var27 = var2.getTypeFactory();
      ICElementFactory var28 = var2.getElementFactory();
      if (this.isCustomOperation()) {
         ICOperator var35 = var26.createCustomOperator(this.getOperationName(), this.getCountOfOperands());
         ICExpression var39 = this.Dw == null ? null : (ICExpression)this.Dw.generateC(var1, var2);
         ICExpression var43 = this.Uv == null ? null : (ICExpression)this.Uv.generateC(var1, var2);
         return var28.createOperation(var35, var39, var43);
      } else if (this.RF.isConversion()) {
         switch (this.RF) {
            case CAST:
            case CAST_S:
               ICExpression var32 = null;
               boolean var38 = false;
               IWildcardType var42 = this.safelyType(var4);
               this.Dw = this.RF(this.Dw);
               IWildcardType var44 = this.Dw.safelyType(var4);
               if (!var42.isResolved()) {
                  if (this.xK == 1) {
                     var38 = true;
                  }

                  IWildcardType.Group var45 = this.RF == OperationType.CAST_S ? IWildcardType.Group.INTEGER : IWildcardType.Group.UINT;
                  IWildcardType var47 = var4.createWithEffectiveBitsize(this.xK).updateGroup(var45);
                  if (var42.isLessSpecializedThan(var47)) {
                     var42 = var47;
                  }
               }

               if (var42.getBitsize() > var44.getBitsize()) {
                  if (this.RF == OperationType.CAST_S) {
                     if (var44.isSigned()) {
                        var38 = true;
                     }
                  } else if (this.RF == OperationType.CAST && var44.isUnsigned()) {
                     var38 = true;
                  }
               }

               IWildcardType var46 = var42.resolveA();
               if (!var46.isResolved() || var46.getBitsize() != var42.getBitsize()) {
                  if (this.xK < this.Dw.getBitsize()) {
                     IEOperation var48 = var1.createOperation(OperationType.AND, this.Dw, EUtil.mask(this.Dw.getBitsize(), this.xK));
                     var32 = (ICExpression)var48.generateC(var1, var2);
                     if (var46.equals(this.Dw.getSafeType(var4).resolveA())) {
                        var46 = null;
                     }
                  } else if (this.xK > this.Dw.getBitsize() && this.RF == OperationType.CAST_S) {
                     var32 = (ICExpression)this.Dw.generateC(var1, var2);
                     ICOperation var34 = var28.createCast(var27.create(var46), var32);
                     ICExpression var49 = (ICExpression)EUtil.mask(var46.getBitsize(), this.xK).generateC(var1, var2);
                     return var28.createOperation(COperatorType.AND, var34, var49);
                  }
               }

               if (var32 == null) {
                  var32 = (ICExpression)this.Dw.generateC(var1, var2);
               }

               if (var46 != null && !var38) {
                  var32 = var28.createCast(var27.create(var46), var32);
               }

               return var32;
            case FP2FP:
            case INT2FP:
            case UINT2FP:
               int var37 = this.getSafeType(var4).getBitsize();
               IWildcardType var31;
               if (var37 == 32) {
                  var31 = var4.create("float");
               } else {
                  var31 = var4.create("double");
               }

               Object var41 = (ICExpression)this.Dw.generateC(var1, var2);
               if (this.Dw.getSafeType(var4).getGroup() != IWildcardType.Group.FLOAT) {
                  var41 = var28.createCast(var27.create(var31), (ICExpression)var41);
               }

               return (ICElement)var41;
            case FP2INT:
            case FP2UINT:
               int var36 = this.getSafeType(var4).getBitsize();
               IWildcardType var30 = var4.createWithEffectiveBitsize(var36)
                  .updateGroup(this.RF == OperationType.FP2UINT ? IWildcardType.Group.UINT : IWildcardType.Group.INTEGER)
                  .resolveA();
               Object var40 = (ICExpression)this.Dw.generateC(var1, var2);
               if (!var30.equals(this.Dw.getType())) {
                  var40 = var28.createCast(var27.create(var30), (ICExpression)var40);
               }

               return (ICElement)var40;
            default:
               throw new RuntimeException();
         }
      } else {
         ICOperator var29 = switch (this.RF) {
            case LOG_AND -> var26.get(COperatorType.LOG_AND);
            case LOG_OR -> var26.get(COperatorType.LOG_OR);
            case LOG_NOT -> var26.get(COperatorType.LOG_NOT);
            case LOG_EQ, FEQ -> var26.get(COperatorType.EQ);
            case LOG_NEQ, FNE -> var26.get(COperatorType.NE);
            case GT_S, GT_U, FGT -> var26.get(COperatorType.GT);
            case GE_S, GE_U, FGE -> var26.get(COperatorType.GE);
            case LT_S, LT_U, FLT -> var26.get(COperatorType.LT);
            case LE_S, LE_U, FLE -> var26.get(COperatorType.LE);
            case PAR -> var26.createCustomOperator("__parity__", 1);
            case CARRY -> var26.createCustomOperator("__carry__", 2);
            default -> var26.createCustomOperator(this.getOperationName(), this.getCountOfOperands());
            case ADD, FADD -> var26.get(COperatorType.ADD);
            case SUB, FSUB -> var26.get(COperatorType.SUB);
            case MUL, FMUL -> var26.get(COperatorType.MUL);
            case DIV_S, DIV_U, FDIV -> var26.get(COperatorType.DIV);
            case REM_S, REM_U -> var26.get(COperatorType.REM);
            case AND -> var26.get(COperatorType.AND);
            case OR -> var26.get(COperatorType.OR);
            case XOR -> var26.get(COperatorType.XOR);
            case ROL -> var26.createCustomOperator("__rol__", 2);
            case ROR -> var26.createCustomOperator("__ror__", 2);
            case SAR -> var26.get(COperatorType.SHR);
            case SHL -> var26.get(COperatorType.SHL);
            case SHR -> var26.get(COperatorType.USHR);
            case NOT -> var26.get(COperatorType.NOT);
            case POW -> var26.createCustomOperator("__pow__", 2);
         };
         if (var29 == null) {
            throw new RuntimeException("Cannot convert IR operator: " + this.RF);
         } else {
            IWildcardType var9 = var4.create("char");
            IWildcardType var10 = var4.create("char*");
            IWildcardType var11 = var4.create("int");
            ICType var12 = var27.create(var10);
            ICType var13 = var27.create(var4.createWithSlotcount(1).updateGroup(IWildcardType.Group.INTEGER));
            IWildcardType var14 = this.Dw == null ? null : this.Dw.getSafeType(var4).resolveA();
            IWildcardType var15 = this.Uv == null ? null : this.Uv.getSafeType(var4).resolveA();
            IWildcardType var16 = this.getType();
            IWildcardType var17 = var14;
            if (var15 != null && var15.isPointer() && !var14.isPointer()) {
               var17 = var15;
            }

            if (this.RF.isLogical()) {
               var17 = var11;
               if (var16 != null && !var16.isDefined()) {
                  var16 = var16.updateMaxBitsize(var11.getBitsize());
               }
            }

            IWildcardType var18 = null;
            Object var19 = this.Dw == null ? null : (ICExpression)this.Dw.generateC(var1, var2);
            Object var20 = this.Uv == null ? null : (ICExpression)this.Uv.generateC(var1, var2);
            IWildcardType.Group var21 = null;
            switch (this.RF) {
               case GT_U:
               case GE_U:
               case LT_U:
               case LE_U:
               case DIV_U:
               case REM_U:
                  var21 = IWildcardType.Group.UINT;
               case GT_S:
               case GE_S:
               case LT_S:
               case LE_S:
               case DIV_S:
               case REM_S:
                  if (var21 == null) {
                     var21 = IWildcardType.Group.INTEGER;
                  }

                  if (!this.RF.isLogical()
                     || var21 != IWildcardType.Group.UINT
                     || !var14.isPointer()
                     || !var15.isPointer()
                     || !var14.isResolved()
                     || !var15.isResolved()
                     || !TypeUtil.same(var14.getNativeType(), var15.getNativeType())) {
                     IWildcardType var22 = this.Dw.getSafeType(var4);
                     IWildcardType.Group var23 = var22.getGroup();
                     if (var23 != null && var23 != var21 && !(var19 instanceof ICConstant)) {
                        ICType var24 = var27.create(var4.createWithEffectiveBitsize(var22.getBitsize()).updateGroup(var21));
                        var19 = var28.createCast(var24, (ICExpression)var19);
                     }

                     var22 = this.Uv.getSafeType(var4);
                     var23 = var22.getGroup();
                     if (var23 != null && var23 != var21 && !(var20 instanceof ICConstant)) {
                        ICType var56 = var27.create(var4.createWithEffectiveBitsize(var22.getBitsize()).updateGroup(var21));
                        var20 = var28.createCast(var56, (ICExpression)var20);
                     }
                  }
               case PAR:
               case CARRY:
               case FEQ:
               case FNE:
               case FLT:
               case FGT:
               case FLE:
               case FGE:
               case FUN:
               case ADD:
               case SUB:
               case MUL:
               default:
                  boolean var51 = false;
                  if (this.RF == OperationType.ADD || this.RF == OperationType.SUB) {
                     if (var14.isPointer()) {
                        int var53 = var14.getPointedSize();
                        if (var53 > 1) {
                           if (this.Uv instanceof alu) {
                              int var57 = (int)((alu)this.Uv).getValueAsLong();
                              if (var57 % var53 == 0) {
                                 var20 = var2.getGlobalContext().getConstantFactory().createInt32(var57 / var53);
                              } else if (var16 != null && var16.isPointer() && var16.getPointedBitsize() == var9.getBitsize()) {
                                 var19 = var28.createCast(var27.create(var16), (ICExpression)var19);
                                 var18 = var16;
                              } else {
                                 var19 = var28.createCast(var12, (ICExpression)var19);
                                 var18 = var10;
                              }

                              var51 = true;
                           }
                        } else if (var53 == 1) {
                           var51 = true;
                        }
                     } else if (var15.isPointer()) {
                        int var54 = var15.getPointedSize();
                        if (var54 <= 1 && var54 == 1) {
                           var51 = true;
                        }
                     }
                  }

                  if (!var51 && !this.RF.isLogical()) {
                     if (var14.isPointer()) {
                        var19 = var28.createOperation(var26.createCastOperator(var13), (ICExpression)var19);
                        var18 = var11;
                     }

                     if (var15 != null && var15.isPointer()) {
                        var20 = var28.createOperation(var26.createCastOperator(var13), (ICExpression)var20);
                        var18 = var11;
                     }
                  }

                  ICOperation var55 = var28.createOperation(var29, (ICExpression)var19, (ICExpression)var20);
                  IWildcardType var58 = null;
                  if (var16 != null && !var16.isUndefined()) {
                     if (var18 != null) {
                        var17 = var18;
                     }

                     if (!var16.resolveA().isEquivalent(var17)) {
                        var58 = var16;
                     }
                  } else if (var18 != null && !var18.equals(var17)) {
                     var58 = var17;
                  }

                  if (var58 != null) {
                     ICOperator var25 = var26.createCastOperator(var27.create(var58));
                     var55 = var28.createOperation(var25, var55);
                  }

                  return var55;
            }
         }
      }
   }

   @Override
   public void q(and var1) {
      if (this.isCustomOperation()) {
         var1.append(this.gO.getName());
         var1.paren();
         int var2 = 0;

         for (IEGeneric var4 : this.getOperands()) {
            if (var2 > 0) {
               var1.append(", ");
            }

            var1.q(var4);
            var2++;
         }

         var1.parenClose();
      } else if (this.Uv == null) {
         var1.append(this.RF.toString());
         if (this.RF.isConversion()) {
            var1.append("_" + this.xK);
         }

         var1.paren();
         var1.q(this.Dw);
         var1.parenClose();
      } else {
         var1.paren();
         var1.q(this.Dw);
         var1.append(" ");
         var1.append(this.RF.toString());
         var1.append(" ");
         var1.q(this.Uv);
         var1.parenClose();
      }

      var1.RF(this);
   }
}
