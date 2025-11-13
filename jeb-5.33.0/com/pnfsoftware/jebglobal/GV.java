package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GV extends kx {
   private final ayy ys;
   private final int ld;
   private final Endianness gp;
   private final aye oT;
   private long fI = -1L;
   private List WR = new ArrayList();

   public GV(a var1) {
      super(var1);
      this.ys = var1.ld();
      this.ld = this.ys.getPointerSize();
      this.oT = this.ys.E();
      this.gp = this.UT.getEndianness();
      this.pC = "data-ptr";
      if (var1.getContainer() instanceof IPECOFFUnit) {
         try {
            long var2 = var1.wS().NS();
            long var4 = var1.getMemory().readLEInt(var2 + 60L);
            this.fI = var2 + var4;
         } catch (MemoryException var6) {
         }
      }
   }

   protected INativeDataItem kS(long var1, long var3, boolean var5) {
      return this.kS(var1, var3);
   }

   @Override
   protected List A(long var1, long var3, boolean var5) {
      INativeDataItem var6 = this.kS(var1, var3, var5);
      if (var6 == null) {
         return Collections.emptyList();
      } else {
         ArrayList var7 = new ArrayList();
         var7.add(var6);
         long var8 = var1;

         for (Long var11 : this.WR) {
            if (var11 + this.ld != var8) {
               break;
            }

            var7.add(this.A.pC(var11, this.oT, -1, true));
            var8 = var11;
         }

         this.WR.clear();
         return var7;
      }
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private INativeDataItem kS(long var1, long var3) {
      if (var3 - var1 < this.ld) {
         return null;
      } else {
         Long var5 = VirtualMemoryUtil.readAsUnsignedLongSafe(this.wS, this.gp, var1, this.ld);
         if (var5 == null) {
            return null;
         } else {
            long var6 = var5;
            if (this.UT.getType().isARM()) {
               if (!this.UT.getType().is64Bit()) {
                  if (var6 > 0L && var6 % this.UT.getInstructionAlignment() == 1L) {
                     var6--;
                  }
               } else if ((var5 & 3L) != 0L) {
                  var6 = 0L;
               }
            }

            if (var5 != 0L && this.A.getAnalysisRanges().contains(var5)) {
               ACLock var8 = this.kS.pC.a();

               Throwable var9;
               label308: {
                  Object var34;
                  label309: {
                     try {
                        var32 = yj.pC(this.A, var5);
                        if (Booleans.isFalse(var32) || this.fI != -1L && var5 < this.fI) {
                           var34 = null;
                           break label309;
                        }
                     } catch (Throwable var31) {
                        var9 = var31;
                        if (var8 == null) {
                           throw var31;
                        }
                        break label308;
                     }

                     INativeDataItem var11;
                     label310: {
                        label283: {
                           try {
                              var10 = Booleans.isTrue(var32) || this.A.vP().pC(var5);
                              if (var10 || (this.ld != 4 || var1 % 4L != 0L) && (this.ld != 8 || var1 % 8L != 0L)) {
                                 break label283;
                              }

                              var33 = yj.A(this.A, var5);
                              if (Booleans.isFalse(var33)) {
                                 var11 = null;
                                 break label310;
                              }
                           } catch (Throwable var30) {
                              var9 = var30;
                              if (var8 == null) {
                                 throw var30;
                              }
                              break label308;
                           }

                           try {
                              var10 = Booleans.isTrue(var33);
                           } catch (Throwable var21) {
                              var9 = var21;
                              if (var8 == null) {
                                 throw var21;
                              }
                              break label308;
                           }
                        }

                        label313: {
                           try {
                              if (var10) {
                                 var11 = this.A.pC(var1, this.oT, -1, true);
                                 break label313;
                              }
                           } catch (Throwable var29) {
                              var9 = var29;
                              if (var8 == null) {
                                 throw var29;
                              }
                              break label308;
                           }

                           if (var6 == 0L) {
                              var11 = null;
                              if (var8 != null) {
                                 var8.close();
                              }

                              return var11;
                           }

                           label314: {
                              try {
                                 if (this.kS.E(var6) != null) {
                                    var11 = this.A.pC(var1, this.oT, -1, true);
                                    break label314;
                                 }
                              } catch (Throwable var28) {
                                 var9 = var28;
                                 if (var8 == null) {
                                    throw var28;
                                 }
                                 break label308;
                              }

                              label315: {
                                 try {
                                    if (this.kS.ys(var6) != null) {
                                       var11 = this.A.pC(var1, this.oT, -1, true);
                                       break label315;
                                    }
                                 } catch (Throwable var27) {
                                    var9 = var27;
                                    if (var8 == null) {
                                       throw var27;
                                    }
                                    break label308;
                                 }

                                 label316: {
                                    try {
                                       if (this.ld == 2 || this.ld == 4 && var1 % 4L != 0L || this.ld == 8 && var1 % 8L != 0L) {
                                          var11 = null;
                                          break label316;
                                       }
                                    } catch (Throwable var26) {
                                       var9 = var26;
                                       if (var8 == null) {
                                          throw var26;
                                       }
                                       break label308;
                                    }

                                    INativeDataItem var42;
                                    label318: {
                                       INativeDataItem var44;
                                       label319: {
                                          INativeDataItem var17;
                                          label320: {
                                             label321: {
                                                try {
                                                   if (!(this.kS.getItemAt(var6) instanceof INativeInstructionItem)) {
                                                      break label321;
                                                   }

                                                   var35 = this.kS.wS(var6, true);
                                                   if (var35.isEmpty()) {
                                                      var42 = this.A.pC(var1, this.oT, -1, true);
                                                      break label318;
                                                   }
                                                } catch (Throwable var24) {
                                                   var9 = var24;
                                                   if (var8 == null) {
                                                      throw var24;
                                                   }
                                                   break label308;
                                                }

                                                label322: {
                                                   INativeDataItem var13;
                                                   try {
                                                      var12 = this.kS.getPreviousItem(var6);
                                                      if (var12.getMemoryAddressEnd() == var6 && var12 instanceof INativeInstructionItem) {
                                                         break label322;
                                                      }

                                                      var13 = this.A.pC(var1, this.oT, -1, true);
                                                   } catch (Throwable var23) {
                                                      var9 = var23;
                                                      if (var8 == null) {
                                                         throw var23;
                                                      }
                                                      break label308;
                                                   }

                                                   if (var8 != null) {
                                                      var8.close();
                                                   }

                                                   return var13;
                                                }

                                                try {
                                                   List var43 = this.kS.wS(var12.getMemoryAddress(), true);
                                                   if (!CollectionUtil.hasIntersection(var35, var43)) {
                                                      var44 = this.A.pC(var1, this.oT, -1, true);
                                                      break label319;
                                                   }
                                                } catch (Throwable var22) {
                                                   var9 = var22;
                                                   if (var8 == null) {
                                                      throw var22;
                                                   }
                                                   break label308;
                                                }

                                                try {
                                                   INativeInstructionItem var14 = (INativeInstructionItem)var12;
                                                   if (!var14.getInstruction().getMnemonic().equalsIgnoreCase("nop")) {
                                                      break label321;
                                                   }

                                                   INativeContinuousItem var15 = this.kS.getPreviousItem(var1);
                                                   if (var15 != null
                                                      && var15.getMemoryAddressEnd() == var1
                                                      && var15 instanceof INativeDataItem var16
                                                      && var16.getType() == this.oT) {
                                                      var17 = this.A.pC(var1, this.oT, -1, true);
                                                      break label320;
                                                   }
                                                } catch (Throwable var25) {
                                                   var9 = var25;
                                                   if (var8 == null) {
                                                      throw var25;
                                                   }
                                                   break label308;
                                                }

                                                try {
                                                   this.WR.add(0, var1);
                                                } catch (Throwable var20) {
                                                   var9 = var20;
                                                   if (var8 == null) {
                                                      throw var20;
                                                   }
                                                   break label308;
                                                }
                                             }

                                             try {
                                                var11 = null;
                                             } catch (Throwable var19) {
                                                var9 = var19;
                                                if (var8 == null) {
                                                   throw var19;
                                                }
                                                break label308;
                                             }

                                             if (var8 != null) {
                                                var8.close();
                                             }

                                             return var11;
                                          }

                                          if (var8 != null) {
                                             var8.close();
                                          }

                                          return var17;
                                       }

                                       if (var8 != null) {
                                          var8.close();
                                       }

                                       return var44;
                                    }

                                    if (var8 != null) {
                                       var8.close();
                                    }

                                    return var42;
                                 }

                                 if (var8 != null) {
                                    var8.close();
                                 }

                                 return var11;
                              }

                              if (var8 != null) {
                                 var8.close();
                              }

                              return var11;
                           }

                           if (var8 != null) {
                              var8.close();
                           }

                           return var11;
                        }

                        if (var8 != null) {
                           var8.close();
                        }

                        return var11;
                     }

                     if (var8 != null) {
                        var8.close();
                     }

                     return var11;
                  }

                  if (var8 != null) {
                     var8.close();
                  }

                  return (INativeDataItem)var34;
               }

               try {
                  var8.close();
               } catch (Throwable var18) {
                  var9.addSuppressed(var18);
               }

               throw var9;
            } else {
               return null;
            }
         }
      }
   }
}
