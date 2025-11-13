package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.android.DexParsingException;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlockBuilder;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.dex.DalvikParserError;
import com.pnfsoftware.jeb.core.units.code.android.dex.DalvikParserErrorType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.CFBytesTrie;
import com.pnfsoftware.jeb.util.collect.IntegerSegment;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ByteBufferUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Ser
@SerVersion(2)
public class beo {
   private static final ILogger kS = GlobalLog.getLogger(beo.class);
   @SerId(3)
   private Map wS;
   @SerId(4)
   private com.pnfsoftware.jeb.corei.parsers.dex.bO UT;
   @SerId(5)
   private beo.Sv E;
   @SerId(6)
   private boolean sY;
   @SerId(7)
   private int ys;
   @SerId(8)
   private int ld;
   @SerId(9)
   private int gp;
   @SerId(10)
   private int oT;
   @SerId(11)
   private boolean fI;
   @SerId(12)
   private ben WR;
   @SerId(value = 14, version = 1)
   private long NS;
   @SerId(value = 15, version = 1)
   private long vP;
   @SerId(value = 16, version = 2)
   private int xC;
   @SerId(17)
   public boolean pC;
   @SerTransient
   private ByteBuffer ED;
   @SerTransient
   private com.pnfsoftware.jeb.corei.parsers.dex.vi Sc;
   @SerTransient
   private bex ah;
   @SerTransient
   private DalvikParserErrorType eP;
   @SerTransient
   private bej UO;
   @SerTransient
   private byte[] Ab;
   @SerTransient
   private int rl;
   @SerTransient
   private List z;
   @SerTransient
   private int Ek;
   @SerTransient
   private bgb hK;
   @SerTransient
   private int Er;
   @SerTransient
   private SegmentMap FE;
   @SerTransient
   private CFBytesTrie Aj;
   @SerTransient
   private long EX;
   @SerTransient
   private long LM;
   @SerTransient
   public int A;

   @SerCustomInitPostGraph
   private void kS() {
      this.FE = new SegmentMap();
      this.Aj = new CFBytesTrie();
   }

   public beo(Map var1, com.pnfsoftware.jeb.corei.parsers.dex.bO var2, beo.Sv var3, int var4, boolean var5, boolean var6) {
      this.wS = var1;
      this.UT = var2;
      this.E = var3;
      this.xC = var4;
      this.sY = var5;
      this.fI = var6;
      this.WR = new ben();
      this.kS();
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.dex.vi var1) {
      this.Sc = var1;
   }

   public void pC(bex var1) {
      this.ah = var1;
   }

   public void pC(byte[] var1) {
      if (var1 != this.Ab) {
         if (var1 == null) {
            this.Ab = null;
            this.ED = null;
         } else {
            this.Ab = var1;
            this.ED = ByteBuffer.wrap(var1);
            this.ED.order(ByteOrder.LITTLE_ENDIAN);
            this.oT = 0;
            this.gp = this.oT;
            this.rl = var1.length;
            this.ld = this.gp + this.rl;
         }
      }
   }

   public void pC(int var1) {
      this.oT += var1;
      this.gp += var1;
   }

   public synchronized beo.Av pC(byte[] var1, int var2, int var3, int var4, bfb[] var5, int var6, bgb var7, int var8) {
      long var9 = System.nanoTime();

      beo.Av var22;
      try {
         if (var1 != null) {
            this.pC(var1);
         }

         this.ys = var2;
         this.rl = var3;
         this.ld = var2 + var3;
         this.gp = var2;
         this.oT = 0;
         if (var2 < 0 || var2 >= var1.length || this.ld > var1.length || this.ld < var2 || var3 % 2 != 0) {
            throw new DexParsingException(Strings.ff("Illegal bytecode parameters: base=%Xh end=%Xh size=%Xh max=%Xh", var2, this.ld, var3, var1.length));
         }

         this.z = new ArrayList();
         this.Ek = var6;
         this.hK = var7;
         this.Er = var8;
         this.FE.clear();
         beo.Av var11 = new beo.Av();
         var11.pC = this.pC(var4, var5);
         var11.kS = this.z;
         if (var11.pC.size() == 0 || var11.pC.get(0).size() == 0) {
            var11.pC = null;
            var11.kS.add(new DalvikParserError(DalvikParserErrorType.EMPTY_CFG));
         }

         try {
            SegmentMap var12 = new SegmentMap();

            for (IntegerSegment var14 : this.FE.values()) {
               var12.add(new IntegerSegment(var14.getBegin() - var2, var14.getSize()));
            }

            if (var11.pC != null) {
               for (BasicBlock var25 : var11.pC) {
                  int var15 = (int)var25.getFirstAddress();
                  int var16 = (int)var25.getEndAddress() - var15;
                  var12.add(new IntegerSegment(var15, var16));
               }
            }

            for (IntegerSegment var27 : var12.generateGapItems(0, true, var3, true, new bep(this), false)) {
               byte[] var28 = Arrays.copyOfRange(var1, var2 + var27.getBegin(), var2 + var27.getEnd());
               var11.A.put(var27.getBegin(), var28);
            }
         } catch (Exception var20) {
            this.z.add(new DalvikParserError(DalvikParserErrorType.GAPS_ERROR));
         }

         var22 = var11;
      } finally {
         this.EX = this.EX + (System.nanoTime() - var9);
      }

      return var22;
   }

   private CFG pC(int var1, bfb[] var2) {
      BasicBlockBuilder var3 = null;
      ArrayList var4 = new ArrayList();
      HashMap var5 = new HashMap();
      TreeMap var6 = new TreeMap();
      ArrayDeque var7 = new ArrayDeque();
      var7.push(this.oT);
      boolean var8 = true;
      int var9 = 0;

      while (true) {
         if (var8) {
            if (var7.isEmpty()) {
               if (var2 == null) {
                  break;
               }

               if (var9 == 0) {
                  Set var26 = pC(var2);
                  var7.addAll(var26);
               } else {
                  if (var9 != 1) {
                     break;
                  }

                  for (bfb var44 : var2) {
                     boolean var47 = false;
                     int var50 = var44.getTryAddress();
                     if (var50 < this.rl) {
                        Integer var16 = (Integer)var6.floorKey(var50);
                        if (var16 != null && var16 == var50) {
                           var7.add(var50);
                           var47 = true;
                        }
                     }

                     if (!var47) {
                        this.z.add(new DalvikParserError(DalvikParserErrorType.BAD_TRY_BLOCK_START_ADDRESS, var50));
                     }

                     boolean var53 = false;
                     int var17 = var44.A();
                     if (var17 <= this.rl) {
                        Integer var18 = (Integer)var6.lowerKey(var17);
                        if (var18 != null) {
                           bek var19 = (bek)var6.get(var18);
                           int var20 = (int)var19.getOffsetEnd();
                           if (var20 == var17) {
                              if (var17 < this.rl && var6.get(var17) != null) {
                                 var7.add(var17);
                              }

                              var53 = true;
                           } else if (var20 > var17 && var20 <= this.rl) {
                              var44.A(var20);
                              if (var20 < this.rl && var6.get(var20) != null) {
                                 var7.add(var20);
                              }

                              var53 = true;
                           }
                        }
                     }

                     if (!var53) {
                        this.z.add(new DalvikParserError(DalvikParserErrorType.BAD_TRY_BLOCK_END_ADDRESS, var17));
                     }
                  }
               }

               var8 = true;
               var9++;
               continue;
            }

            this.oT = (Integer)var7.pop();
            this.gp = this.ys + this.oT;
            var3 = (BasicBlockBuilder)var5.get(this.oT);
            if (var3 != null) {
               if (((bek)var3.insns.get(0)).getOffset() == this.oT) {
                  continue;
               }

               int var25 = -1;
               int var30 = 0;

               for (bek var41 : var3.insns) {
                  if (var41.getOffset() == this.oT) {
                     var25 = var30;
                     break;
                  }

                  var30++;
               }

               if (var25 < 0) {
                  this.z.add(new DalvikParserError(DalvikParserErrorType.OVERLAPPING_INSTRUCTIONS, this.oT));
                  var8 = true;
                  continue;
               }

               BasicBlockBuilder var37 = new BasicBlockBuilder();
               var4.add(var37);

               for (int var31 = var25; var31 < var3.insns.size(); var31++) {
                  bek var42 = (bek)var3.insns.get(var31);
                  var37.insns.add(var42);
                  var5.put((int)var42.getOffset(), var37);
               }

               var37.dst_offsets = new ArrayList(var3.dst_offsets);
               int var43 = var3.insns.size() - var25;

               for (int var32 = 0; var32 < var43; var32++) {
                  var3.insns.remove(var25);
               }

               var3.dst_offsets.clear();
               var3.dst_offsets.add((long)this.oT);
               continue;
            }

            var3 = new BasicBlockBuilder();
            var4.add(var3);
            var8 = false;
         }

         BasicBlockBuilder var10 = (BasicBlockBuilder)var5.get(this.oT);
         if (var10 != null) {
            if (var3.insns.isEmpty()) {
               throw new RuntimeException("Unexpected empty basic block");
            }

            var3.dst_offsets.add(((bek)var10.insns.get(0)).getOffset());
            var8 = true;
         } else {
            bek var11 = this.pC();
            if (this.eP != null) {
               this.z.add(new DalvikParserError(this.eP, this.gp - this.ys));
               this.pC(this.eP);
               if (var11 == null) {
                  var8 = true;
                  continue;
               }
            }

            var3.insns.add(var11);
            var5.put(this.oT, var3);
            var6.put(this.oT, var11);
            IFlowInformation var12 = var11.getBreakingFlow();
            if (!var12.isBroken()) {
               this.gp = this.gp + var11.getSize();
               this.oT = this.oT + var11.getSize();
            } else {
               for (ICodePointer var14 : var12.getTargets()) {
                  long var15 = var14.getAddress();
                  if (var15 >= 0L && var15 < this.rl) {
                     var3.dst_offsets.add(var15);
                     var7.push((int)var15);
                  }
               }

               var8 = true;
            }
         }
      }

      if (var2 != null) {
         for (bfb var45 : var2) {
            int var48 = var45.getTryAddress();
            int var51 = var45.A();
            List var54 = var45.UT();

            for (BasicBlockBuilder var58 : var4) {
               if (!var58.insns.isEmpty()) {
                  int var60 = (int)((bek)var58.insns.get(0)).getOffset();
                  if (var60 >= var48 && var60 < var51) {
                     for (int var21 : var54) {
                        long var22 = var21 & 4294967295L;
                        var58.irrdst_offsets.add(var22);
                     }
                  }
               }
            }
         }
      }

      if (var1 >= 0) {
         label164:
         for (BasicBlockBuilder var35 : var4) {
            for (bek var46 : var35.insns) {
               for (bem var57 : var46.A()) {
                  int var59 = var57.getType();
                  if (var59 == 0) {
                     int var61 = (int)var57.getValue();
                     if (var61 < 0 || var61 >= var1) {
                        this.z.add(new DalvikParserError(DalvikParserErrorType.REGISTER_OUTSIDE_FRAME));
                        break label164;
                     }
                  } else if (var59 == 4) {
                     int var62 = (int)var57.getValue();
                     int var64 = (int)(var57.getValue() >> 32);
                     if (var62 < 0 || var62 > var64 || var64 >= var1) {
                        this.z.add(new DalvikParserError(DalvikParserErrorType.REGISTER_OUTSIDE_FRAME));
                        break label164;
                     }
                  }
               }
            }
         }
      }

      try {
         return new CFG(var4);
      } catch (Exception var24) {
         throw var24;
      }
   }

   private void pC(DalvikParserErrorType var1) {
      if (this.ah != null) {
         this.ah.pC(var1, this.ys, this.rl, this.gp - this.ys, this.Ek, this.UO == null ? null : this.UO.pC);
      } else {
         String var2 = Strings.ff(S.L("Offset %Xh (method=%Xh/%Xh): Parsing error (%s)"), this.gp, this.ys, this.rl, var1);
         if (this.UO != null && this.UO.pC != null) {
            var2 = var2 + Strings.ff(" (%s)", this.UO.pC.kS);
         }

         if (this.Sc == null) {
            kS.error(var2);
         } else {
            this.Sc.logError(true, var2);
         }
      }
   }

   public bek pC() {
      this.eP = null;
      this.UO = null;
      if (this.gp >= this.ys && this.gp < this.ld) {
         if (this.FE != null && this.FE.getSegmentContaining(this.gp) != null) {
            this.eP = DalvikParserErrorType.OUT_OF_BOUNDARIES;
            return null;
         } else {
            int var1 = this.ED.get(this.gp) & 255;
            bel.Av var2 = bel.pC[var1];
            byte var3 = 0;
            if (var1 == 255 && this.sY && this.xC < 50) {
               if (this.gp + 1 >= this.ld) {
                  this.eP = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                  return null;
               }

               int var4 = this.ED.get(this.gp + 1) & 255;
               if (var4 >= bel.wS.length) {
                  this.eP = DalvikParserErrorType.INVALID_EXTENDED_OPCODE;
                  return null;
               }

               var1 = this.ED.getShort(this.gp) & '\uffff';
               var2 = bel.wS[var4];
               var3 = 2;
            }

            if (this.Er < var2.ys || var2.kS == null || var2.kS.length() == 0) {
               int var24 = var1 - 227;
               if (this.xC < 50 && var24 >= 0 && var24 < bel.A.length && bel.A[var24] != null) {
                  if (!this.fI) {
                     this.eP = DalvikParserErrorType.OPTIMIZED_OPCODE;
                     return null;
                  }

                  var2 = bel.A[var24];
                  var3 = 1;
               } else {
                  var24 = var1 - 115;
                  if (this.xC >= 50 && var24 >= 0 && var24 < bel.kS.length && bel.kS[var24] != null) {
                     if (!this.fI) {
                        this.eP = DalvikParserErrorType.OPTIMIZED_OPCODE;
                        return null;
                     }

                     var2 = bel.kS[var24];
                     var3 = 3;
                  } else {
                     this.eP = DalvikParserErrorType.RESERVED_OPCODE;
                  }
               }
            }

            if (var2.pC == 254 || var2.pC == 255) {
               this.A++;
            }

            int var26 = 2 * pC(var2);
            if (this.gp + var26 > this.gp && this.gp + var26 <= this.ld) {
               bek var5 = new bek();
               var5.pC = this.gp - this.ys;
               long var6 = System.nanoTime();
               bej var8 = (bej)this.Aj.get(this.ED.array(), this.ED.arrayOffset() + this.gp, this.ED.arrayOffset() + this.ld, false);
               this.LM = this.LM + (System.nanoTime() - var6);
               if (var8 == null || var8.A() || this.hK != null && var8.kS()) {
                  var8 = new bej();
                  var5.A = var8;
                  var8.A = ByteBufferUtil.getBytes(this.ED, this.gp, var26);
                  var8.pC = var2;
                  var8.sY = Integer.valueOf(var3);
                  this.UO = var8;
                  int var9 = this.ED.get(this.gp + 1) & 255;
                  ArrayList var10 = new ArrayList(3);
                  if (pC(var2, "10x")) {
                     if (var2.kS == null || var2.kS.length() == 0) {
                        this.eP = DalvikParserErrorType.RESERVED_OPCODE;
                     }
                  } else if (pC(var2, "12x", "11n")) {
                     int var11 = var9 & 15;
                     int var12 = var9 >> 4 & 15;
                     var10.add(this.WR.pC(0, var11));
                     if (pC(var2, "12x")) {
                        var10.add(this.WR.pC(0, var12));
                     } else if (pC(var2, "11n")) {
                        long var13 = (var12 & 8) == 0 ? var12 : var12 | -16L;
                        var10.add(this.WR.pC(1, var13));
                     }
                  } else if (pC(var2, "11x", "10t")) {
                     if (pC(var2, "11x")) {
                        var10.add(this.WR.pC(0, var9));
                     } else if (pC(var2, "10t")) {
                        byte var29 = this.ED.get(this.gp + 1);
                        var10.add(this.WR.pC(3, var29));
                     }
                  } else if (pC(var2, "20t")) {
                     if (var9 != 0) {
                        this.eP = DalvikParserErrorType.INVALID_OPCODE_FORMAT;
                     }

                     short var30 = this.ED.getShort(this.gp + 2);
                     var10.add(this.WR.pC(3, var30));
                  } else if (pC(var2, "20bc")) {
                     int var31 = this.ED.get(this.gp + 1) & 255;
                     long var49 = this.ED.getShort(this.gp + 2) & '\uffff';
                     var10.add(this.WR.pC(1, var31));
                     var10.add(this.WR.pC(1, var49));
                  } else if (pC(var2, "22x", "21t", "21s", "21h", "21c")) {
                     int var32 = this.ED.get(this.gp + 1) & 255;
                     long var50 = this.ED.getShort(this.gp + 2);
                     var10.add(this.WR.pC(0, var32));
                     if (pC(var2, "22x")) {
                        var10.add(this.WR.pC(0, var50 & 65535L));
                     } else if (pC(var2, "21t")) {
                        var10.add(this.WR.pC(3, var50));
                     } else if (pC(var2, "21s")) {
                        var10.add(this.WR.pC(1, var50));
                     } else if (pC(var2, "21h")) {
                        if (var1 == 21) {
                           var10.add(this.WR.pC(1, var50 << 16));
                        } else {
                           var10.add(this.WR.pC(1, var50 << 48));
                        }
                     } else {
                        var10.add(this.WR.pC(2, this.pC(var2, (int)(var50 & 65535L))));
                     }
                  } else if (pC(var2, "23x", "22b")) {
                     int var33 = this.ED.get(this.gp + 1) & 255;
                     int var51 = this.ED.get(this.gp + 2) & 255;
                     var10.add(this.WR.pC(0, var33));
                     var10.add(this.WR.pC(0, var51));
                     if (pC(var2, "23x")) {
                        int var66 = this.ED.get(this.gp + 3) & 255;
                        var10.add(this.WR.pC(0, var66));
                     } else {
                        byte var67 = this.ED.get(this.gp + 3);
                        var10.add(this.WR.pC(1, var67));
                     }
                  } else if (pC(var2, "22t", "22s", "22c", "22cs")) {
                     int var34 = this.ED.get(this.gp + 1) & 15;
                     int var52 = this.ED.get(this.gp + 1) >> 4 & 15;
                     var10.add(this.WR.pC(0, var34));
                     var10.add(this.WR.pC(0, var52));
                     short var68 = this.ED.getShort(this.gp + 2);
                     if (pC(var2, "22t")) {
                        if (var34 == var52 && !this.pC) {
                           var10.clear();
                           var8.pC = bel.pC[41];
                           var8.sY = 0;
                           var8.A[0] = 41;
                           var8.A[1] = 0;
                           if (var1 != 50) {
                              var8.A[2] = 2;
                              var8.A[3] = 0;
                              var68 = 2;
                           }
                        }

                        var10.add(this.WR.pC(3, var68));
                     } else if (pC(var2, "22s")) {
                        var10.add(this.WR.pC(1, var68));
                     } else if (pC(var2, "22c")) {
                        var10.add(this.WR.pC(2, this.pC(var2, var68 & '\uffff')));
                     } else {
                        if (!pC(var2, "22cs")) {
                           this.eP = DalvikParserErrorType.UNIMPLEMENTED_ENCODING_FORMAT;
                           return null;
                        }

                        var10.add(this.WR.pC(2, this.pC(var2, var68 & '\uffff')));
                     }
                  } else if (pC(var2, "30t")) {
                     if (this.ED.get(this.gp + 1) != 0) {
                        this.eP = DalvikParserErrorType.INVALID_OPCODE_FORMAT;
                     }

                     int var35 = this.ED.getInt(this.gp + 2);
                     var10.add(this.WR.pC(3, var35));
                  } else if (pC(var2, "32x")) {
                     if (this.ED.get(this.gp + 1) != 0) {
                        this.eP = DalvikParserErrorType.INVALID_OPCODE_FORMAT;
                     }

                     int var36 = this.ED.getShort(this.gp + 2) & '\uffff';
                     int var53 = this.ED.getShort(this.gp + 4) & '\uffff';
                     var10.add(this.WR.pC(0, var36));
                     var10.add(this.WR.pC(0, var53));
                  } else if (pC(var2, "31i", "31t", "31c")) {
                     int var37 = this.ED.get(this.gp + 1) & 255;
                     int var54 = this.ED.getInt(this.gp + 2);
                     var10.add(this.WR.pC(0, var37));
                     if (pC(var2, "31i")) {
                        var10.add(this.WR.pC(1, var54));
                     } else if (pC(var2, "31t")) {
                        int var69 = this.gp + 2 * var54;
                        if (var69 < this.ys || var69 > this.ld) {
                           this.eP = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                           return null;
                        }

                        byte var14 = 0;

                        while (var69 + var14 + 2 <= this.ld && this.ED.getShort(var69 + var14) == 0) {
                           var14 += 2;
                        }

                        if (var14 != 0) {
                           this.pC(DalvikParserErrorType.PADDING_BEFORE_PSEUDO_OPCODE);
                        }

                        int var15 = var69 + var14;
                        int var16 = var14 / 2;
                        var10.add(this.WR.pC(3, var54 + var16));
                        if (var2.kS.equals("packed-switch")) {
                           int var17 = var15 + 8;
                           if (var17 < var15 || var17 > this.ld) {
                              this.eP = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                              return null;
                           }

                           if (this.ED.getShort(var15) != 256) {
                              this.eP = DalvikParserErrorType.INVALID_PSEUDO_INSTRUCTION_ID;
                              return null;
                           }

                           int var18 = this.ED.getShort(var15 + 2) & '\uffff';
                           int var19 = this.ED.getInt(var15 + 4);
                           int var20 = 8 + 4 * var18;
                           var17 = var15 + var20;
                           if (var17 < var15 || var17 > this.ld) {
                              this.eP = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                              return null;
                           }

                           bey var21 = new bey(var15 - this.ys, var18);

                           for (int var22 = 0; var22 < var18; var22++) {
                              int var23 = this.ED.getInt(var15 + 8 + 4 * var22);
                              var21.A[var22] = new bey.Av(var19 + var22, var23);
                           }

                           var8.wS = var21;
                           IntegerSegment var117 = (IntegerSegment)this.FE.get(var15);
                           if (var117 != null) {
                              Assert.a(var117.getSize() == var20);
                           } else {
                              this.FE.add(new IntegerSegment(var15, var20));
                           }
                        } else if (var2.kS.equals("sparse-switch")) {
                           int var95 = var15 + 4;
                           if (var95 < var15 || var95 > this.ld) {
                              this.eP = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                              return null;
                           }

                           if (this.ED.getShort(var15) != 512) {
                              this.eP = DalvikParserErrorType.INVALID_PSEUDO_INSTRUCTION_ID;
                              return null;
                           }

                           int var103 = this.ED.getShort(var15 + 2) & '\uffff';
                           int var107 = 4 + 8 * var103;
                           var95 = var15 + var107;
                           if (var95 < var15 || var95 > this.ld) {
                              this.eP = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                              return null;
                           }

                           bey var110 = new bey(var15 - this.ys, var103);
                           int[] var114 = new int[var103];

                           for (int var118 = 0; var118 < var103; var118++) {
                              var114[var118] = this.ED.getInt(var15 + 4 + 4 * var118);
                           }

                           for (int var119 = 0; var119 < var103; var119++) {
                              int var123 = this.ED.getInt(var15 + 4 + 4 * var103 + 4 * var119);
                              var110.A[var119] = new bey.Av(var114[var119], var123);
                           }

                           var8.wS = var110;
                           IntegerSegment var120 = (IntegerSegment)this.FE.get(var15);
                           if (var120 != null) {
                              Assert.a(var120.getSize() == var107);
                           } else {
                              this.FE.add(new IntegerSegment(var15, var107));
                           }
                        } else if (var2.kS.equals("fill-array-data")) {
                           int var97 = var15 + 8;
                           if (var97 < var15 || var97 > this.ld) {
                              this.eP = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                              return null;
                           }

                           if (this.ED.getShort(var15) != 768) {
                              this.eP = DalvikParserErrorType.INVALID_PSEUDO_INSTRUCTION_ID;
                              return null;
                           }

                           int var104 = this.ED.getShort(var15 + 2) & '\uffff';
                           int var108 = this.ED.getInt(var15 + 4);
                           int var111 = 2 * ((var108 * var104 + 1) / 2 + 4);
                           var97 = var15 + var111;
                           if (var97 < var15 || var97 > this.ld) {
                              this.eP = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                              return null;
                           }

                           beh var115 = new beh(var15 - this.ys, var108, var104);

                           for (int var121 = 0; var121 < var108; var121++) {
                              for (int var124 = 0; var124 < var104; var124++) {
                                 var115.A[var121][var124] = this.ED.get(var15 + 8 + var121 * var104 + var124);
                              }
                           }

                           var8.UT = var115;
                           IntegerSegment var122 = (IntegerSegment)this.FE.get(var15);
                           if (var122 != null) {
                              Assert.a(var122.getSize() == var111);
                           } else {
                              this.FE.add(new IntegerSegment(var15, var111));
                           }
                        }
                     } else {
                        var10.add(this.WR.pC(2, this.pC(var2, var54)));
                     }
                  } else if (pC(var2, "35c", "35ms", "35mi", "45cc")) {
                     int var38 = var9 >> 4;
                     if (var38 > 5) {
                        this.eP = DalvikParserErrorType.INVALID_ARGUMENT_COUNT;
                        var38 = 5;
                     }

                     int var55 = this.ED.getShort(this.gp + 2) & '\uffff';
                     int[] var70 = new int[]{0, 0, 0, 0, 0};
                     short var79 = this.ED.getShort(this.gp + 4);
                     var70[0] = var79 & 15;
                     var70[1] = var79 >> 4 & 15;
                     var70[2] = var79 >> 8 & 15;
                     var70[3] = var79 >> 12 & 15;
                     var70[4] = var9 & 15;
                     var10.add(this.WR.pC(2, this.pC(var2, var55)));
                     if (pC(var2, "45cc")) {
                        int var87 = this.ED.getShort(this.gp + 6) & '\uffff';
                        var10.add(this.WR.pC(5, this.A(var2, var87)));
                     }

                     for (int var88 = 0; var88 < var38; var88++) {
                        var10.add(this.WR.pC(0, var70[var88]));
                     }
                  } else if (pC(var2, "3rc", "3rms", "3rmi", "4rcc")) {
                     int var39 = this.ED.get(this.gp + 1) & 255;
                     int var56 = this.ED.getShort(this.gp + 2) & '\uffff';
                     int var71 = this.ED.getShort(this.gp + 4) & '\uffff';
                     var10.add(this.WR.pC(2, this.pC(var2, var56)));
                     if (pC(var2, "4rcc")) {
                        int var80 = this.ED.getShort(this.gp + 6) & '\uffff';
                        var10.add(this.WR.pC(5, this.A(var2, var80)));
                     }

                     long var81 = var71 | (long)(var71 + var39 - 1) << 32;
                     var10.add(this.WR.pC(4, var81));
                  } else if (pC(var2, "51l")) {
                     int var40 = this.ED.get(this.gp + 1) & 255;
                     long var57 = this.ED.getLong(this.gp + 2);
                     var10.add(this.WR.pC(0, var40));
                     var10.add(this.WR.pC(1, var57));
                  } else if (pC(var2, "41c")) {
                     int var41 = this.ED.getInt(this.gp + 2);
                     int var58 = this.ED.getShort(this.gp + 6) & '\uffff';
                     var10.add(this.WR.pC(0, var58));
                     var10.add(this.WR.pC(2, this.pC(var2, var41)));
                  } else if (pC(var2, "52c")) {
                     int var42 = this.ED.getInt(this.gp + 2);
                     int var59 = this.ED.getShort(this.gp + 6) & '\uffff';
                     int var72 = this.ED.getShort(this.gp + 8) & '\uffff';
                     var10.add(this.WR.pC(0, var59));
                     var10.add(this.WR.pC(0, var72));
                     var10.add(this.WR.pC(2, this.pC(var2, var42)));
                  } else {
                     if (!pC(var2, "5rc")) {
                        this.eP = DalvikParserErrorType.UNIMPLEMENTED_ENCODING_FORMAT;
                        return null;
                     }

                     int var43 = this.ED.getInt(this.gp + 2);
                     int var60 = this.ED.getShort(this.gp + 6) & '\uffff';
                     int var73 = this.ED.getShort(this.gp + 8) & '\uffff';
                     var10.add(this.WR.pC(2, this.pC(var2, var43)));
                     long var82 = var73 | (long)(var73 + var60 - 1) << 32;
                     var10.add(this.WR.pC(4, var82));
                  }

                  if (this.E != null && var3 == 0) {
                     for (bem var61 : var10) {
                        int var74 = var61.getType();
                        if (var74 == 2 || var74 == 5) {
                           int var83 = (int)var61.getValue();
                           if (var83 < 0) {
                              this.eP = DalvikParserErrorType.INVALID_INDEX;
                              break;
                           }

                           int var89;
                           if (var74 == 2) {
                              var89 = var2.UT & 15;
                           } else {
                              var89 = var2.UT >> 8 & 15;
                           }
                           if (var83 >= switch (var89) {
                              case 1 -> this.E.pC;
                              case 2 -> this.E.A;
                              case 3 -> this.E.kS;
                              case 4 -> this.E.wS;
                              case 5 -> this.E.UT;
                              case 6 -> this.E.E;
                              default -> throw new RuntimeException("Unknown index type for instruction: " + var89);
                              case 10 -> this.E.sY;
                           }) {
                              this.eP = DalvikParserErrorType.INVALID_INDEX;
                           }
                        }
                     }
                  }

                  var8.kS = new bem[var10.size()];
                  var10.toArray(var8.kS);
                  this.Aj.put(var8.A, var8);
                  if (this.wS != null) {
                     for (bem var62 : var10) {
                        if (var62.getType() == 1 && !this.wS.containsKey(var62.getValue())) {
                           long var75 = var62.getValue();
                           this.wS.put(var75, var75);
                        }
                     }

                     if (var5.isSwitch()) {
                        for (bey.Av var84 : var5.wS()) {
                           long var90 = var84.pC() & 4294967295L;
                           this.wS.put(var90, var90);
                        }
                     }

                     if (var5.isArray()) {
                        for (long var85 : var5.UT().asArrayOfLongs()) {
                           this.wS.put(var85, var85);
                        }
                     }
                  }
               } else {
                  var5.A = var8;
                  this.vP++;
               }

               this.NS++;
               if (this.UT != null && var3 == 0) {
                  for (bem var65 : var8.kS) {
                     int var78 = var65.getType();
                     if (var78 == 2 || var78 == 5) {
                        int var86 = (int)var65.getValue();
                        if (var86 >= 0) {
                           int var91;
                           if (var78 == 2) {
                              var91 = var2.UT & 15;
                           } else {
                              var91 = var2.UT >> 8 & 15;
                           }

                           int var93 = var2.UT & 0xFF0000;
                           switch (var91) {
                              case 1:
                                 if (this.E == null || var86 < this.E.pC) {
                                    this.UT.pC(var86, this.Ek, this.oT, com.pnfsoftware.jeb.corei.parsers.dex.DH.A);
                                 }
                                 break;
                              case 2:
                                 if (this.E != null && var86 >= this.E.A) {
                                    break;
                                 }

                                 int var102 = 0;
                                 if ((var93 & 262144) != 0) {
                                    var102 = com.pnfsoftware.jeb.corei.parsers.dex.DH.gp;
                                 }

                                 this.UT.A(var86, this.Ek, this.oT, var102);
                                 break;
                              case 3:
                                 if (this.E != null && var86 >= this.E.kS) {
                                    break;
                                 }

                                 int var101 = 0;
                                 if ((var93 & 131072) != 0) {
                                    var101 = com.pnfsoftware.jeb.corei.parsers.dex.DH.UT;
                                 } else if ((var93 & 65536) != 0) {
                                    var101 = com.pnfsoftware.jeb.corei.parsers.dex.DH.wS;
                                 }

                                 this.UT.kS(var86, this.Ek, this.oT, var101);
                                 break;
                              case 4:
                                 if (this.E == null || var86 < this.E.wS) {
                                    this.UT.wS(var86, this.Ek, this.oT, com.pnfsoftware.jeb.corei.parsers.dex.DH.E);
                                 }
                              case 5:
                                 break;
                              case 6:
                                 if (this.Sc == null || var86 >= this.Sc.getCallSites().size()) {
                                    break;
                                 }

                                 bfr var100 = this.Sc.ld(var86);

                                 for (IDexValue var109 : var100.getCallSiteValues()) {
                                    if (var109.getType() == 23) {
                                       int var112 = var109.getStringIndex();
                                       this.UT.pC(var112, this.Ek, this.oT, com.pnfsoftware.jeb.corei.parsers.dex.DH.kS);
                                    } else if (var109.getType() == 22) {
                                       bfv var113 = this.Sc.gp(var109.getMethodHandleIndex());
                                       if (var113.getMethodHandleType().isMethodInvoker()) {
                                          this.UT.wS(var113.getFieldOrMethodIndex(), this.Ek, this.oT, com.pnfsoftware.jeb.corei.parsers.dex.DH.ld);
                                       } else {
                                          int var116 = 0;
                                          if (var113.getMethodHandleType().isFieldSetter()) {
                                             var116 = com.pnfsoftware.jeb.corei.parsers.dex.DH.ys;
                                          } else if (var113.getMethodHandleType().isFieldGetter()) {
                                             var116 = com.pnfsoftware.jeb.corei.parsers.dex.DH.sY;
                                          }

                                          this.UT.kS(var113.getFieldOrMethodIndex(), this.Ek, this.oT, var116);
                                       }
                                    }
                                 }
                                 break;
                              case 7:
                              case 8:
                              case 9:
                              default:
                                 throw new RuntimeException("Unknown index type for instruction: " + var91);
                              case 10:
                                 if (this.Sc != null && var86 < this.Sc.getMethodHandles().size()) {
                                    bfv var99 = this.Sc.gp(var86);
                                    if (var99.getMethodHandleType().isMethodInvoker()) {
                                       this.UT.wS(var99.getFieldOrMethodIndex(), this.Ek, this.oT, com.pnfsoftware.jeb.corei.parsers.dex.DH.ld);
                                    } else {
                                       int var105 = 0;
                                       if (var99.getMethodHandleType().isFieldSetter()) {
                                          var105 = com.pnfsoftware.jeb.corei.parsers.dex.DH.ys;
                                       } else if (var99.getMethodHandleType().isFieldGetter()) {
                                          var105 = com.pnfsoftware.jeb.corei.parsers.dex.DH.sY;
                                       }

                                       this.UT.kS(var99.getFieldOrMethodIndex(), this.Ek, this.oT, var105);
                                    }
                                 }
                           }
                        }
                     }
                  }
               }

               return var5;
            } else {
               this.eP = DalvikParserErrorType.OUT_OF_BOUNDARIES;
               return null;
            }
         }
      } else {
         this.eP = DalvikParserErrorType.OUT_OF_BOUNDARIES;
         return null;
      }
   }

   public long A() {
      return this.NS;
   }

   private long pC(bel.Av var1, int var2) {
      return this.pC(var1.UT & 15, var2);
   }

   private long A(bel.Av var1, int var2) {
      return this.pC(var1.UT >> 8 & 15, var2);
   }

   private long pC(int var1, int var2) {
      if (this.hK == null) {
         return var2;
      } else {
         switch (var1) {
            case 1:
               return this.hK.pC(var2);
            case 2:
               return this.hK.A(var2);
            case 3:
               return this.hK.wS(var2);
            case 4:
               return this.hK.UT(var2);
            case 5:
               return this.hK.kS(var2);
            case 6:
               return this.hK.E(var2);
            case 7:
            case 8:
            case 9:
            default:
               return var2;
            case 10:
               return this.hK.sY(var2);
         }
      }
   }

   private static boolean pC(bel.Av var0, String var1) {
      return var0.A.equals(var1);
   }

   private static boolean pC(bel.Av var0, String var1, String var2) {
      return var0.A.equals(var1) || var0.A.equals(var2);
   }

   private static boolean pC(bel.Av var0, String var1, String var2, String var3) {
      return var0.A.equals(var1) || var0.A.equals(var2) || var0.A.equals(var3);
   }

   private static boolean pC(bel.Av var0, String var1, String var2, String var3, String var4) {
      return var0.A.equals(var1) || var0.A.equals(var2) || var0.A.equals(var3) || var0.A.equals(var4);
   }

   private static boolean pC(bel.Av var0, String var1, String var2, String var3, String var4, String var5) {
      return var0.A.equals(var1) || var0.A.equals(var2) || var0.A.equals(var3) || var0.A.equals(var4) || var0.A.equals(var5);
   }

   private static int pC(bel.Av var0) {
      if (var0.A.length() != 3 && var0.A.length() != 4) {
         throw new RuntimeException("Invalid instruction format for definition: " + var0);
      } else {
         return var0.A.codePointAt(0) - 48;
      }
   }

   private static Set pC(bfb[] var0) {
      LinkedHashSet var1 = new LinkedHashSet();

      for (bfb var5 : var0) {
         var1.addAll(var5.UT());
      }

      return var1;
   }

   public static String A(int var0) {
      switch (var0) {
         case 0:
            return "LEGACY";
         case 50:
            return "ART";
         case 100:
            return "DEX38";
         case 110:
            return "DEX39";
         case 1000:
            return "LATEST";
         default:
            return Strings.ff("UNKNOWN(%d)", var0);
      }
   }

   public static class Av {
      public CFG pC;
      public Map A = new TreeMap();
      public List kS;
   }

   @Ser
   @SerVersion(2)
   public static class Sv {
      @SerId(1)
      int pC;
      @SerId(2)
      int A;
      @SerId(3)
      int kS;
      @SerId(4)
      int wS;
      @SerId(value = 5, version = 1)
      int UT;
      @SerId(value = 6, version = 1)
      int E;
      @SerId(value = 7, version = 2)
      int sY;

      public Sv(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
         this.UT = var5;
         this.E = var6;
         this.sY = var7;
      }
   }
}
