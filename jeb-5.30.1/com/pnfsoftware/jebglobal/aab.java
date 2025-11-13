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

public class aab extends PA {
   private final bby nf;
   private final int gP;
   private final Endianness za;
   private final bbd lm;
   private long zz = -1L;
   private List JY = new ArrayList();

   public aab(aae var1) {
      super(var1);
      this.nf = var1.zz();
      this.gP = this.nf.getPointerSize();
      this.lm = this.nf.gP();
      this.za = this.Uv.getEndianness();
      this.q = "data-ptr";
      if (var1.getContainer() instanceof IPECOFFUnit) {
         try {
            long var2 = var1.Dw().io();
            long var4 = var1.getMemory().readLEInt(var2 + 60L);
            this.zz = var2 + var4;
         } catch (MemoryException var6) {
         }
      }
   }

   protected INativeDataItem xK(long var1, long var3, boolean var5) {
      return this.xK(var1, var3);
   }

   @Override
   protected List RF(long var1, long var3, boolean var5) {
      INativeDataItem var6 = this.xK(var1, var3, var5);
      if (var6 == null) {
         return Collections.emptyList();
      } else {
         ArrayList var7 = new ArrayList();
         var7.add(var6);
         long var8 = var1;

         for (Long var11 : this.JY) {
            if (var11 + this.gP != var8) {
               break;
            }

            var7.add(this.RF.q(var11, this.lm, -1, true));
            var8 = var11;
         }

         this.JY.clear();
         return var7;
      }
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private INativeDataItem xK(long var1, long var3) {
      if (var3 - var1 < this.gP) {
         return null;
      } else {
         Long var5 = VirtualMemoryUtil.readAsUnsignedLongSafe(this.Dw, this.za, var1, this.gP);
         if (var5 == null) {
            return null;
         } else {
            long var6 = var5;
            if (this.Uv.getType().isARM()) {
               if (!this.Uv.getType().is64Bit()) {
                  if (var6 > 0L && var6 % this.Uv.getInstructionAlignment() == 1L) {
                     var6--;
                  }
               } else if ((var5 & 3L) != 0L) {
                  var6 = 0L;
               }
            }

            if (var5 != 0L && this.RF.getAnalysisRanges().contains(var5)) {
               ACLock var8 = this.xK.q.a();

               Throwable var9;
               label306: {
                  Object var33;
                  label307: {
                     try {
                        var31 = aaj.q(this.RF, var5);
                        if (Booleans.isFalse(var31) || this.zz != -1L && var5 < this.zz) {
                           var33 = null;
                           break label307;
                        }
                     } catch (Throwable var30) {
                        var9 = var30;
                        if (var8 == null) {
                           throw var30;
                        }
                        break label306;
                     }

                     INativeDataItem var11;
                     label308: {
                        label281: {
                           try {
                              var10 = Booleans.isTrue(var31) || this.RF.Hk().q(var5);
                              if (var10 || (this.gP != 4 || var1 % 4L != 0L) && (this.gP != 8 || var1 % 8L != 0L)) {
                                 break label281;
                              }

                              var32 = aaj.RF(this.RF, var5);
                              if (Booleans.isFalse(var32)) {
                                 var11 = null;
                                 break label308;
                              }
                           } catch (Throwable var29) {
                              var9 = var29;
                              if (var8 == null) {
                                 throw var29;
                              }
                              break label306;
                           }

                           try {
                              var10 = Booleans.isTrue(var32);
                           } catch (Throwable var20) {
                              var9 = var20;
                              if (var8 == null) {
                                 throw var20;
                              }
                              break label306;
                           }
                        }

                        label311: {
                           try {
                              if (var10) {
                                 var11 = this.RF.q(var1, this.lm, -1, true);
                                 break label311;
                              }
                           } catch (Throwable var28) {
                              var9 = var28;
                              if (var8 == null) {
                                 throw var28;
                              }
                              break label306;
                           }

                           if (var6 == 0L) {
                              var11 = null;
                              if (var8 != null) {
                                 var8.close();
                              }

                              return var11;
                           }

                           label312: {
                              try {
                                 if (this.xK.oW(var6) != null) {
                                    var11 = this.RF.q(var1, this.lm, -1, true);
                                    break label312;
                                 }
                              } catch (Throwable var27) {
                                 var9 = var27;
                                 if (var8 == null) {
                                    throw var27;
                                 }
                                 break label306;
                              }

                              label313: {
                                 try {
                                    if (this.xK.nf(var6) != null) {
                                       var11 = this.RF.q(var1, this.lm, -1, true);
                                       break label313;
                                    }
                                 } catch (Throwable var26) {
                                    var9 = var26;
                                    if (var8 == null) {
                                       throw var26;
                                    }
                                    break label306;
                                 }

                                 label314: {
                                    try {
                                       if (this.gP == 2 || this.gP == 4 && var1 % 4L != 0L || this.gP == 8 && var1 % 8L != 0L) {
                                          var11 = null;
                                          break label314;
                                       }
                                    } catch (Throwable var25) {
                                       var9 = var25;
                                       if (var8 == null) {
                                          throw var25;
                                       }
                                       break label306;
                                    }

                                    INativeDataItem var41;
                                    label316: {
                                       INativeDataItem var43;
                                       label317: {
                                          INativeDataItem var16;
                                          label318: {
                                             label319: {
                                                try {
                                                   if (!(this.xK.getItemAt(var6) instanceof INativeInstructionItem)) {
                                                      break label319;
                                                   }

                                                   var34 = this.xK.Dw(var6, true);
                                                   if (var34.isEmpty()) {
                                                      var41 = this.RF.q(var1, this.lm, -1, true);
                                                      break label316;
                                                   }
                                                } catch (Throwable var23) {
                                                   var9 = var23;
                                                   if (var8 == null) {
                                                      throw var23;
                                                   }
                                                   break label306;
                                                }

                                                label320: {
                                                   INativeDataItem var13;
                                                   try {
                                                      var12 = this.xK.getPreviousItem(var6);
                                                      if (var12.getMemoryAddressEnd() == var6 && var12 instanceof INativeInstructionItem) {
                                                         break label320;
                                                      }

                                                      var13 = this.RF.q(var1, this.lm, -1, true);
                                                   } catch (Throwable var22) {
                                                      var9 = var22;
                                                      if (var8 == null) {
                                                         throw var22;
                                                      }
                                                      break label306;
                                                   }

                                                   if (var8 != null) {
                                                      var8.close();
                                                   }

                                                   return var13;
                                                }

                                                try {
                                                   List var42 = this.xK.Dw(var12.getMemoryAddress(), true);
                                                   if (!CollectionUtil.hasIntersection(var34, var42)) {
                                                      var43 = this.RF.q(var1, this.lm, -1, true);
                                                      break label317;
                                                   }
                                                } catch (Throwable var21) {
                                                   var9 = var21;
                                                   if (var8 == null) {
                                                      throw var21;
                                                   }
                                                   break label306;
                                                }

                                                try {
                                                   INativeInstructionItem var14 = (INativeInstructionItem)var12;
                                                   if (!var14.getInstruction().getMnemonic().equalsIgnoreCase("nop")) {
                                                      break label319;
                                                   }

                                                   INativeContinuousItem var15 = this.xK.getPreviousItem(var1);
                                                   if (var15.getMemoryAddressEnd() == var1
                                                      && var15 instanceof INativeDataItem
                                                      && ((INativeDataItem)var15).getType() == this.lm) {
                                                      var16 = this.RF.q(var1, this.lm, -1, true);
                                                      break label318;
                                                   }
                                                } catch (Throwable var24) {
                                                   var9 = var24;
                                                   if (var8 == null) {
                                                      throw var24;
                                                   }
                                                   break label306;
                                                }

                                                try {
                                                   this.JY.add(0, var1);
                                                } catch (Throwable var19) {
                                                   var9 = var19;
                                                   if (var8 == null) {
                                                      throw var19;
                                                   }
                                                   break label306;
                                                }
                                             }

                                             try {
                                                var11 = null;
                                             } catch (Throwable var18) {
                                                var9 = var18;
                                                if (var8 == null) {
                                                   throw var18;
                                                }
                                                break label306;
                                             }

                                             if (var8 != null) {
                                                var8.close();
                                             }

                                             return var11;
                                          }

                                          if (var8 != null) {
                                             var8.close();
                                          }

                                          return var16;
                                       }

                                       if (var8 != null) {
                                          var8.close();
                                       }

                                       return var43;
                                    }

                                    if (var8 != null) {
                                       var8.close();
                                    }

                                    return var41;
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

                  return (INativeDataItem)var33;
               }

               try {
                  var8.close();
               } catch (Throwable var17) {
                  var9.addSuppressed(var17);
               }

               throw var9;
            } else {
               return null;
            }
         }
      }
   }
}
