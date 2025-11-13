package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

public class EExpressionGenerator {
   private IERoutineContext ctx;
   private INode template;
   private Map map;
   private IdentityHashSet usedForGen;

   public EExpressionGenerator(IERoutineContext var1, INode var2) {
      if (var1 != null && var2 != null) {
         this.ctx = var1;
         this.template = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public IEGeneric generate(Map var1) {
      return this.generate(var1, null);
   }

   IEGeneric generate(Map var1, Integer var2) {
      if (this.usedForGen != null) {
         throw new IllegalStateException();
      } else {
         this.usedForGen = new IdentityHashSet();
         this.map = var1;

         IEGeneric var3;
         try {
            var3 = this.generate(this.template, var2);
         } finally {
            this.map = null;
            this.usedForGen.clear();
         }

         return var3;
      }
   }

   private IEGeneric generate(INode var1) {
      return this.generate(var1, null);
   }

   private IEGeneric generate(INode var1, Integer var2) {
      if (var1 instanceof Node var14) {
         O var15 = var14.operator;
         if (var15 == null) {
            int var16 = var14.opgrp.id;
            var15 = (O)this.map.get(var16);
            if (var15 == null) {
               return null;
            }
         }

         switch (var15) {
            case SLICE:
               IEGeneric var28 = this.generate(var14.opnds[0]);
               int var35 = ((Leaf)var14.opnds[1]).value.intValue();
               int var39 = ((Leaf)var14.opnds[2]).value.intValue();
               return var28.slice(var35, var39);
            case SLICE_FIRSTBIT:
               IEGeneric var27 = this.generate(var14.opnds[0]);
               return var27.lsb();
            case SLICE_LASTBIT:
               IEGeneric var26 = this.generate(var14.opnds[0]);
               return var26.msb();
            case SLICE_FIRST32:
               IEGeneric var25 = this.generate(var14.opnds[0]);
               return var25.part(32);
            case SLICE_HALF1:
               IEGeneric var24 = this.generate(var14.opnds[0]);
               return var24.part(var24.getBitsize() / 2);
            case SLICE_HALF2:
               IEGeneric var23 = this.generate(var14.opnds[0]);
               return var23.slice(var23.getBitsize() / 2, var23.getBitsize());
            case COMPOSE_2:
               IEGeneric var22 = this.generate(var14.opnds[0]);
               IEGeneric var34 = this.generate(var14.opnds[1], var2 == null ? null : var2 - var22.getBitsize());
               return this.ctx.createCompose(var22, var34);
            case COMPOSE_2EQ:
               IEGeneric var21 = this.generate(var14.opnds[0], var2 == null ? null : var2 / 2);
               IEGeneric var33 = this.generate(var14.opnds[1], var2 == null ? var21.getBitsize() : var2 / 2);
               if (var21.getBitsize() != var33.getBitsize()) {
                  throw new RuntimeException();
               }

               return this.ctx.createCompose(var21, var33);
            case COND:
               IEGeneric var20 = this.generate(var14.opnds[0]);
               IEGeneric var32 = this.generate(var14.opnds[1], var2 == null ? null : var2);
               IEGeneric var38 = this.generate(var14.opnds[2], var32.getBitsize());
               return this.ctx.createCond(var20, var32, var38);
            case TRN:
            case TRN8:
            case TRN16:
            case TRN32:
            case TRN64:
            case TRN128:
               int var31 = var15.getResultingBitsize();
               int var19;
               if (var31 == 0) {
                  if (var2 == null) {
                     throw new RuntimeException();
                  }

                  var19 = var2;
               } else {
                  if (var2 != null && var2 != var31) {
                     throw new RuntimeException();
                  }

                  var19 = var31;
               }

               IEGeneric var37 = this.generate(var14.opnds[0]);
               return this.ctx.createResizeOperation(var37, var19, false);
            case EXT:
            case EXT8:
            case EXT16:
            case EXT32:
            case EXT64:
            case EXT128:
               int var30 = var15.getResultingBitsize();
               int var18;
               if (var30 == 0) {
                  if (var2 == null) {
                     throw new RuntimeException();
                  }

                  var18 = var2;
               } else {
                  if (var2 != null && var2 != var30) {
                     throw new RuntimeException();
                  }

                  var18 = var30;
               }

               IEGeneric var36 = this.generate(var14.opnds[0]);
               return this.ctx.createResizeOperation(var36, var18, true);
            default:
               OperationType var17 = var15.getOperationType();
               if (var17 == null) {
                  return null;
               } else {
                  Integer var29 = null;
                  if (var2 != null) {
                     switch (var17) {
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
                           var29 = var2;
                     }
                  }

                  IEGeneric[] var7 = new IEGeneric[3];
                  int var8 = 0;

                  for (INode var12 : var14.opnds) {
                     if (var8 >= var7.length) {
                        return null;
                     }

                     IEGeneric var13 = this.generate(var12, var29);
                     if (var13 == null) {
                        return null;
                     }

                     if (var29 == null) {
                        var29 = var13.getBitsize();
                     }

                     var7[var8] = var13;
                     var8++;
                  }

                  return this.ctx.createOperation(var17, var7[0], var7[1]);
               }
         }
      } else {
         Leaf var3 = (Leaf)var1;
         if (var3.flags == 16 && var3.id == -1) {
            if (var3.optionalBitsize != 0) {
               return this.ctx.createImm(var3.value.longValue(), var3.optionalBitsize);
            }

            if (var2 != null) {
               return this.ctx.createImm(var3.value.longValue(), var2);
            }
         }

         IEGeneric var4 = (IEGeneric)this.map.get(var3.id);
         if (var4 == null) {
            throw new RuntimeException(Strings.ff("Expression not found (leaf_id=%d). Are you using LC() in a substituter? \"Very sad!\" (c) DJT", var3.id));
         } else {
            if (var3.flags == 16) {
               int var5 = var4.getBitsize();
               if (var3.optionalBitsize != 0 || var5 == 0) {
                  throw new RuntimeException("Illegal bitsize values");
               }

               IEImm var6 = this.ctx.createImm(var3.value.longValue(), var5);
               if (!(var4 instanceof IEImm) || !var4.equalsEx(var6, false)) {
                  return var6;
               }
            }

            if (!this.usedForGen.add(var4)) {
               var4 = var4.duplicate();
            }

            return var4;
         }
      }
   }
}
