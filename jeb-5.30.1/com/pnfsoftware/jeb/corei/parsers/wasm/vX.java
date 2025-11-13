package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Ser
public class vX extends AbstractProcessor {
   private static final ILogger RF = GlobalLog.getLogger(vX.class);
   @SerId(1)
   qx q;

   public vX() {
      this(null);
   }

   public vX(qx var1) {
      super(1, 32, Endianness.LITTLE_ENDIAN, 1);
      this.q = var1;
   }

   public List q(int var1, int var2, boolean var3) throws ProcessorException {
      if (this.q == null) {
         throw new IllegalStateException();
      } else {
         int var4 = var1;
         ArrayList var5 = new ArrayList();
         int var6 = 0;
         int var7 = -1;
         ArrayDeque var8 = new ArrayDeque();
         HashMap var9 = new HashMap();
         int var10 = 0;
         boolean var11 = false;
         int var12 = -1;

         while (var4 < var2) {
            SG var13 = (SG)this.parseAt(this.q.q, var4, var2);
            var13.Uv = var4 - var1;
            int var14 = var13.q();
            int var15 = var6;
            int var16 = var10;
            Integer var17 = null;
            boolean var18 = false;
            if (var14 == 2 || var14 == 3 || var14 == 4) {
               var8.push(var13);
               var6++;
               var7++;
            } else if (var14 == 15) {
               if (var4 + 2 == var2) {
                  var11 = true;
               }

               var17 = var3 ? 0 : -1;
            } else if (var14 == 11) {
               if (var4 + 1 == var2) {
                  Assert.a(var6 == 0, "Final END opcode should be at depth 0");
                  if (!var11 && !var3 && var12 != 0) {
                     var17 = -1;
                  }
               }

               if (var6 > 0) {
                  SG var19 = (SG)var8.pop();
                  if (var19.oW()) {
                     var19.q(var13.xK());
                  } else if (var19.gO()) {
                     SG var20 = (SG)var9.get(var19.Uv);
                     var19.q(var20.xK());
                     var20.q(var13.xK());
                  }

                  var19.JY = var13;
                  var13.JY = var19;
                  int var35 = var19.lm();
                  var17 = var35 != 64 ? 1 : 0;
                  if (var19.gP + var17 != var10) {
                     String var21 = Strings.ff("Unexpected stack delta: %d, %d", var19.gP + var17, var10);
                     Object[] var10000 = new Object[]{var21};
                  }

                  var10 = var19.gP + var17;
                  var18 = true;
               }

               var6--;
            } else if (var14 == 5) {
               if (var8.isEmpty() || ((SG)var8.peek()).q() != 4) {
                  throw new ProcessorException("Unexpected ELSE does not match IF");
               }

               SG var30 = (SG)var8.peek();
               var30.lm |= 1;
               var9.put(var30.Uv, var13);
               var10 = var30.gP;
               var18 = true;
            } else if (var14 == 12 || var14 == 13) {
               int var32 = var13.za();
               ArrayList var37 = new ArrayList(var8);
               SG var41 = (SG)var37.get(var32);
               var13.HF = var41;
            } else if (var14 == 14) {
               eo var31 = (eo)var13.nf()[0].Dw();
               ArrayList var36 = new ArrayList(var8);
               var13.LK = new ArrayList(var31.q.size());

               for (int var22 : var31.q) {
                  var13.LK.add((SG)var36.get(var22));
               }

               var13.LK.add((SG)var36.get(var31.RF));
            }

            var13.oW = var15;
            var13.gO = var6;
            var13.za = var7;
            if (!var18) {
               if (var17 == null) {
                  var17 = var13.RF.Uv();
                  if (var17 == null) {
                     if (var14 == 16) {
                        int var33 = var13.za();
                        iZ var38 = this.q.RF(var33).q;
                        var17 = (var38.q == null ? 0 : 1) - var38.RF.length;
                     } else {
                        if (var14 != 17) {
                           throw new RuntimeException("Unknown stack delta for: " + var13);
                        }

                        int var34 = var13.za();
                        iZ var39 = this.q.oW(var34);
                        var17 = (var39.q == null ? 0 : 1) - var39.RF.length - 1;
                     }
                  }
               }

               var10 += var17;
               if (var10 < 0) {
                  throw new RuntimeException("Resulting stack delta is negative: " + var10);
               }
            }

            var13.nf = var16;
            var13.gP = var10;
            var4 += var13.getSize();
            var5.add(var13);
            var12 = var13.q();
            if (var6 < 0) {
               break;
            }
         }

         if (var10 != 0) {
            String var23 = Strings.ff("Expected a zero stack-delta at EOF, got: " + var10);
            Object[] var42 = new Object[]{var23};
         }

         for (SG var25 : var5) {
            int var26 = var25.q();
            if (var26 == 12 || var26 == 13) {
               SG var27 = var25.HF;
               if (var27.q() != 2 && var27.q() != 4) {
                  if (var27.q() != 3) {
                     throw new RuntimeException();
                  }

                  var25.q(var27.RF());
               } else {
                  var25.q(var27.JY.xK());
               }
            } else if (var26 == 14) {
               for (SG var29 : var25.LK) {
                  if (var29.q() != 2 && var29.q() != 4) {
                     if (var29.q() != 3) {
                        throw new RuntimeException();
                     }

                     var25.q(var29.RF());
                  } else {
                     var25.q(var29.JY.xK());
                  }
               }
            }
         }

         if (var4 != var2) {
            throw new ProcessorException();
         } else if (var5.isEmpty()) {
            throw new ProcessorException();
         } else if (!((SG)var5.get(var5.size() - 1)).Dw()) {
            throw new ProcessorException();
         } else {
            return var5;
         }
      }
   }

   public SG q(int var1) throws ProcessorException {
      if (this.q == null) {
         throw new IllegalStateException("Null wasm unit");
      } else {
         return this.q(this.q.q, var1, this.q.q.length);
      }
   }

   public SG q(IVirtualMemory var1, long var2) throws ProcessorException {
      if (this.q == null) {
         return (SG)super.parseAt(var1, var2);
      } else {
         oM var4 = null;
         int var5 = -1;

         for (Nt var7 : this.q.Uv) {
            if (var2 >= var7.xK && var2 < var7.xK + var7.Dw) {
               var4 = var7.RF;
               var5 = (int)(var2 - var7.xK);
               break;
            }
         }

         if (var4 == null) {
            throw new ProcessorException();
         } else {
            int var9 = 0;

            for (SG var8 : var4.RF) {
               if (var9 == var5) {
                  return var8;
               }

               var9 += var8.getSize();
            }

            throw new ProcessorException();
         }
      }
   }

   protected SG q(byte[] var1, int var2, int var3) throws ProcessorException {
      if (var2 < var1.length && var2 < var3) {
         int var4 = var2 + 1;
         int var5 = var1[var2] & 255;
         ME[] var6;
         if (var5 == 252) {
            var6 = Fw.sE;
            kY.eo var7 = kY.gP(var1, var4);
            var4 += var7.RF;
            var5 = (int)var7.q;
         } else if (var5 == 253) {
            var6 = Fw.BR;
            var5 = -1;
         } else if (var5 == 254) {
            var6 = Fw.wh;
            var5 = var1[var4++] & 255;
         } else {
            var6 = Fw.rw;
         }

         if (var5 >= var6.length) {
            throw new ProcessorException("Unsupported opcode");
         } else {
            ME var25 = var6[var5];
            SG var8 = new SG(var25);
            ArrayList var9 = new ArrayList();

            for (int var13 : var25.gO) {
               int var15 = var13 & 0xFF;

               Object var14 = switch (var15) {
                  case 1 -> {
                     kY.eo var38 = kY.gP(var1, var4);
                     var4 += var38.RF;
                     int var43 = (int)var38.q;
                     ArrayList var45 = new ArrayList(var43);

                     for (int var46 = 0; var46 < var43; var46++) {
                        var38 = kY.gP(var1, var4);
                        var4 += var38.RF;
                        int var20 = (int)var38.q;
                        var45.add(var20);
                     }

                     var38 = kY.gP(var1, var4);
                     var4 += var38.RF;
                     int var47 = (int)var38.q;
                     eo var48 = new eo(var45, var47);
                     yield var48;
                  }
                  case 2 -> {
                     int var37 = kY.q(var1, var4);
                     if (var37 != 64 && !Xa.oW(var37)) {
                        throw new RuntimeException("Unsupported block_type: " + var37);
                     }

                     var4++;
                     yield (long)var37;
                  }
                  case 3 -> {
                     kY.eo var35 = kY.gP(var1, var4);
                     var4 += var35.RF;
                     int var42 = (int)var35.q;
                     var35 = kY.gP(var1, var4);
                     var4 += var35.RF;
                     int var44 = (int)var35.q;
                     yield var44 & 4294967295L | (long)var42 << 32;
                  }
                  case 4 -> {
                     int var34 = kY.q(var1, var4);
                     yield var34;
                  }
                  case 5 -> {
                     kY.eo var33 = kY.gP(var1, var4);
                     var4 += var33.RF;
                     yield var33.q;
                  }
                  case 6 -> {
                     kY.eo var32 = kY.Uv(var1, var4);
                     var4 += var32.RF;
                     yield var32.q;
                  }
                  case 7 -> {
                     kY.eo var31 = kY.oW(var1, var4);
                     var4 += var31.RF;
                     yield var31.q;
                  }
                  case 8 -> {
                     int var30 = kY.RF(var1, var4);
                     var4 += 4;
                     yield var30 & 4294967295L;
                  }
                  case 9 -> {
                     long var29 = kY.xK(var1, var4);
                     var4 += 8;
                     yield var29;
                  }
                  case 10, 11, 12, 13 -> {
                     int var16 = var1[var4] & 255;
                     var4++;

                     if (var16 != switch (var15) {
                        case 10 -> 0;
                        case 11 -> 1;
                        case 12 -> 2;
                        case 13 -> 3;
                        default -> throw new RuntimeException();
                     }) {
                        throw new ProcessorException();
                     }

                     kY.eo var18 = kY.gP(var1, var4);
                     var4 += var18.RF;
                     int var19 = (int)var18.q;
                     yield var19 & 4294967295L | (long)var16 << 32;
                  }
                  default -> throw new RuntimeException("TBI: opndcode: " + var13);
               };

               if (var14 != null) {
                  ct var41 = new ct(var13, var14);
                  var9.add(var41);
               }
            }

            if ((var25.xK & 2) == 2) {
               int var26 = kY.q(var1, var4);
               if (var26 != 0) {
                  throw new ProcessorException();
               }

               var4++;
            }

            if ((var25.xK & 4) == 4) {
               int var27 = kY.q(var1, var4);
               if (var27 != 0) {
                  throw new ProcessorException();
               }

               int var28 = kY.q(var1, ++var4);
               if (var28 != 0) {
                  throw new ProcessorException();
               }

               var4++;
            }

            var8.xK = Arrays.copyOfRange(var1, var2, var4);
            var8.Dw = (ct[])var9.toArray(new ct[var9.size()]);
            return var8;
         }
      } else {
         throw new ProcessorException("Not enough bytes");
      }
   }

   static {
      Fw.q = true;
   }
}
