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
public class gJ extends AbstractProcessor {
   private static final ILogger A = GlobalLog.getLogger(gJ.class);
   @SerId(1)
   Hv pC;

   public gJ() {
      this(null);
   }

   public gJ(Hv var1) {
      super(1, 32, Endianness.LITTLE_ENDIAN, 1);
      this.pC = var1;
   }

   public List pC(int var1, int var2, boolean var3) throws ProcessorException {
      if (this.pC == null) {
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
            m var13 = (m)this.parseAt(this.pC.pC, var4, var2);
            var13.wS = var4 - var1;
            int var14 = var13.pC();
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
                  m var19 = (m)var8.pop();
                  if (var19.E()) {
                     var19.pC(var13.kS());
                  } else if (var19.sY()) {
                     m var20 = (m)var9.get(var19.wS);
                     var19.pC(var20.kS());
                     var20.pC(var13.kS());
                  }

                  var19.fI = var13;
                  var13.fI = var19;
                  int var35 = var19.oT();
                  var17 = var35 != 64 ? 1 : 0;
                  if (var19.ys + var17 != var10) {
                     String var21 = Strings.ff("Unexpected stack delta: %d, %d", var19.ys + var17, var10);
                     Object[] var10000 = new Object[]{var21};
                  }

                  var10 = var19.ys + var17;
                  var18 = true;
               }

               var6--;
            } else if (var14 == 5) {
               if (var8.isEmpty() || ((m)var8.peek()).pC() != 4) {
                  throw new ProcessorException("Unexpected ELSE does not match IF");
               }

               m var30 = (m)var8.peek();
               var30.gp |= 1;
               var9.put(var30.wS, var13);
               var10 = var30.ys;
               var18 = true;
            } else if (var14 == 12 || var14 == 13) {
               int var32 = var13.gp();
               ArrayList var37 = new ArrayList(var8);
               m var41 = (m)var37.get(var32);
               var13.WR = var41;
            } else if (var14 == 14) {
               Av var31 = (Av)var13.ys()[0].kS();
               ArrayList var36 = new ArrayList(var8);
               var13.NS = new ArrayList(var31.pC.size());

               for (int var22 : var31.pC) {
                  var13.NS.add((m)var36.get(var22));
               }

               var13.NS.add((m)var36.get(var31.A));
            }

            var13.UT = var15;
            var13.E = var6;
            var13.ld = var7;
            if (!var18) {
               if (var17 == null) {
                  var17 = var13.pC.A();
                  if (var17 == null) {
                     if (var14 == 16) {
                        int var33 = var13.gp();
                        DH var38 = this.pC.pC(var33).pC;
                        var17 = (var38.pC == null ? 0 : 1) - var38.A.length;
                     } else {
                        if (var14 != 17) {
                           throw new RuntimeException("Unknown stack delta for: " + var13);
                        }

                        int var34 = var13.gp();
                        DH var39 = this.pC.kS(var34);
                        var17 = (var39.pC == null ? 0 : 1) - var39.A.length - 1;
                     }
                  }
               }

               var10 += var17;
               if (var10 < 0) {
                  throw new RuntimeException("Resulting stack delta is negative: " + var10);
               }
            }

            var13.sY = var16;
            var13.ys = var10;
            var4 += var13.getSize();
            var5.add(var13);
            var12 = var13.pC();
            if (var6 < 0) {
               break;
            }
         }

         if (var10 != 0) {
            String var23 = Strings.ff("Expected a zero stack-delta at EOF, got: " + var10);
            Object[] var42 = new Object[]{var23};
         }

         for (m var25 : var5) {
            int var26 = var25.pC();
            if (var26 == 12 || var26 == 13) {
               m var27 = var25.WR;
               if (var27.pC() != 2 && var27.pC() != 4) {
                  if (var27.pC() != 3) {
                     throw new RuntimeException();
                  }

                  var25.pC(var27.A());
               } else {
                  var25.pC(var27.fI.kS());
               }
            } else if (var26 == 14) {
               for (m var29 : var25.NS) {
                  if (var29.pC() != 2 && var29.pC() != 4) {
                     if (var29.pC() != 3) {
                        throw new RuntimeException();
                     }

                     var25.pC(var29.A());
                  } else {
                     var25.pC(var29.fI.kS());
                  }
               }
            }
         }

         if (var4 != var2) {
            throw new ProcessorException();
         } else if (var5.isEmpty()) {
            throw new ProcessorException();
         } else if (!((m)var5.get(var5.size() - 1)).wS()) {
            throw new ProcessorException();
         } else {
            return var5;
         }
      }
   }

   public m pC(IVirtualMemory var1, long var2) throws ProcessorException {
      if (this.pC == null) {
         return (m)super.parseAt(var1, var2);
      } else {
         bO var4 = null;
         int var5 = -1;

         for (cq var7 : this.pC.UT) {
            if (var2 >= var7.kS && var2 < var7.kS + var7.wS) {
               var4 = var7.A;
               var5 = (int)(var2 - var7.kS);
               break;
            }
         }

         if (var4 == null) {
            throw new ProcessorException();
         } else {
            int var9 = 0;

            for (m var8 : var4.A) {
               if (var9 == var5) {
                  return var8;
               }

               var9 += var8.getSize();
            }

            throw new ProcessorException();
         }
      }
   }

   protected m pC(byte[] var1, int var2, int var3) throws ProcessorException {
      if (var2 < var1.length && var2 < var3) {
         int var4 = var2 + 1;
         int var5 = var1[var2] & 255;
         nA[] var6;
         if (var5 == 252) {
            var6 = eW.ys;
            uX.Av var7 = uX.E(var1, var4);
            var4 += var7.A;
            var5 = (int)var7.pC;
         } else if (var5 == 253) {
            var6 = eW.ld;
            var5 = -1;
         } else if (var5 == 254) {
            var6 = eW.gp;
            var5 = var1[var4++] & 255;
         } else {
            var6 = eW.sY;
         }

         if (var5 >= var6.length) {
            throw new ProcessorException("Unsupported opcode");
         } else {
            nA var25 = var6[var5];
            m var8 = new m(var25);
            ArrayList var9 = new ArrayList();

            for (int var13 : var25.sY) {
               int var15 = var13 & 0xFF;

               Object var14 = switch (var15) {
                  case 1 -> {
                     uX.Av var38 = uX.E(var1, var4);
                     var4 += var38.A;
                     int var43 = (int)var38.pC;
                     ArrayList var45 = new ArrayList(var43);

                     for (int var46 = 0; var46 < var43; var46++) {
                        var38 = uX.E(var1, var4);
                        var4 += var38.A;
                        int var20 = (int)var38.pC;
                        var45.add(var20);
                     }

                     var38 = uX.E(var1, var4);
                     var4 += var38.A;
                     int var47 = (int)var38.pC;
                     Av var48 = new Av(var45, var47);
                     yield var48;
                  }
                  case 2 -> {
                     int var37 = uX.pC(var1, var4);
                     if (var37 != 64 && !Tb.E(var37)) {
                        throw new RuntimeException("Unsupported block_type: " + var37);
                     }

                     var4++;
                     yield (long)var37;
                  }
                  case 3 -> {
                     uX.Av var35 = uX.E(var1, var4);
                     var4 += var35.A;
                     int var42 = (int)var35.pC;
                     var35 = uX.E(var1, var4);
                     var4 += var35.A;
                     int var44 = (int)var35.pC;
                     yield var44 & 4294967295L | (long)var42 << 32;
                  }
                  case 4 -> {
                     int var34 = uX.pC(var1, var4);
                     yield var34;
                  }
                  case 5 -> {
                     uX.Av var33 = uX.E(var1, var4);
                     var4 += var33.A;
                     yield var33.pC;
                  }
                  case 6 -> {
                     uX.Av var32 = uX.wS(var1, var4);
                     var4 += var32.A;
                     yield var32.pC;
                  }
                  case 7 -> {
                     uX.Av var31 = uX.UT(var1, var4);
                     var4 += var31.A;
                     yield var31.pC;
                  }
                  case 8 -> {
                     int var30 = uX.A(var1, var4);
                     var4 += 4;
                     yield var30 & 4294967295L;
                  }
                  case 9 -> {
                     long var29 = uX.kS(var1, var4);
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

                     uX.Av var18 = uX.E(var1, var4);
                     var4 += var18.A;
                     int var19 = (int)var18.pC;
                     yield var19 & 4294967295L | (long)var16 << 32;
                  }
                  default -> throw new RuntimeException("TBI: opndcode: " + var13);
               };

               if (var14 != null) {
                  jM var41 = new jM(var13, var14);
                  var9.add(var41);
               }
            }

            if ((var25.kS & 2) == 2) {
               int var26 = uX.pC(var1, var4);
               if (var26 != 0) {
                  throw new ProcessorException();
               }

               var4++;
            }

            if ((var25.kS & 4) == 4) {
               int var27 = uX.pC(var1, var4);
               if (var27 != 0) {
                  throw new ProcessorException();
               }

               int var28 = uX.pC(var1, ++var4);
               if (var28 != 0) {
                  throw new ProcessorException();
               }

               var4++;
            }

            var8.A = Arrays.copyOfRange(var1, var2, var4);
            var8.kS = (jM[])var9.toArray(new jM[var9.size()]);
            return var8;
         }
      } else {
         throw new ProcessorException("Not enough bytes");
      }
   }

   static {
      eW.pC = true;
   }
}
