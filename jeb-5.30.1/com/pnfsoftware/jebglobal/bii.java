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
import com.pnfsoftware.jeb.util.format.TimeFormatter;
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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Ser
@SerVersion(2)
public class bii {
   private static final ILogger lm = GlobalLog.getLogger(bii.class);
   public static final int q = 0;
   public static final int RF = 50;
   public static final int xK = 100;
   public static final int Dw = 110;
   public static final int Uv = 120;
   public static final int oW = 130;
   public static final int gO = 1000;
   public static final int nf = 50;
   @SerId(3)
   private Map zz;
   @SerId(4)
   private com.pnfsoftware.jeb.corei.parsers.dex.oM JY;
   @SerId(5)
   private bii.CU HF;
   @SerId(6)
   private boolean LK;
   @SerId(7)
   private int io;
   @SerId(8)
   private int qa;
   @SerId(9)
   private int Hk;
   @SerId(10)
   private int Me;
   @SerId(11)
   private boolean PV;
   @SerId(12)
   private bih oQ;
   @SerId(value = 14, version = 1)
   private long xW;
   @SerId(value = 15, version = 1)
   private long KT;
   @SerId(value = 16, version = 2)
   private int Gf;
   @SerId(17)
   public boolean gP;
   @SerTransient
   private ByteBuffer Ef;
   @SerTransient
   private com.pnfsoftware.jeb.corei.parsers.dex.bK cC;
   @SerTransient
   private bir sH;
   @SerTransient
   private DalvikParserErrorType CE;
   @SerTransient
   private bid wF;
   @SerTransient
   private byte[] If;
   @SerTransient
   private int Dz;
   @SerTransient
   private List mI;
   @SerTransient
   private int jq;
   @SerTransient
   private bjw ui;
   @SerTransient
   private int TX;
   @SerTransient
   private SegmentMap Rr;
   @SerTransient
   private CFBytesTrie EB;
   @SerTransient
   private long Xo;
   @SerTransient
   private long Bu;
   @SerTransient
   public int za;

   @SerCustomInitPostGraph
   private void Dw() {
      this.Rr = new SegmentMap();
      this.EB = new CFBytesTrie();
   }

   public bii(Map var1, com.pnfsoftware.jeb.corei.parsers.dex.oM var2, bii.CU var3, int var4, boolean var5, boolean var6) {
      this.zz = var1;
      this.JY = var2;
      this.HF = var3;
      this.Gf = var4;
      this.LK = var5;
      this.PV = var6;
      this.oQ = new bih();
      this.Dw();
   }

   public void q(com.pnfsoftware.jeb.corei.parsers.dex.bK var1) {
      this.cC = var1;
   }

   public void q(bir var1) {
      this.sH = var1;
   }

   public void q(byte[] var1) {
      if (var1 != this.If) {
         if (var1 == null) {
            this.If = null;
            this.Ef = null;
         } else {
            this.If = var1;
            this.Ef = ByteBuffer.wrap(var1);
            this.Ef.order(ByteOrder.LITTLE_ENDIAN);
            this.Me = 0;
            this.Hk = this.Me;
            this.Dz = var1.length;
            this.qa = this.Hk + this.Dz;
         }
      }
   }

   public void q(int var1) {
      this.Me += var1;
      this.Hk += var1;
   }

   public synchronized bii.eo q(byte[] var1, int var2, int var3, int var4, biw[] var5, int var6, bjw var7, int var8) {
      long var9 = System.nanoTime();

      bii.eo var22;
      try {
         if (var1 != null) {
            this.q(var1);
         }

         this.io = var2;
         this.Dz = var3;
         this.qa = var2 + var3;
         this.Hk = var2;
         this.Me = 0;
         if (var2 < 0 || var2 >= var1.length || this.qa > var1.length || this.qa < var2 || var3 % 2 != 0) {
            throw new DexParsingException(Strings.ff("Illegal bytecode parameters: base=%Xh end=%Xh size=%Xh max=%Xh", var2, this.qa, var3, var1.length));
         }

         this.mI = new ArrayList();
         this.jq = var6;
         this.ui = var7;
         this.TX = var8;
         this.Rr.clear();
         bii.eo var11 = new bii.eo();
         var11.q = this.q(var4, var5);
         var11.xK = this.mI;
         if (var11.q.size() == 0 || var11.q.get(0).size() == 0) {
            var11.q = null;
            var11.xK.add(new DalvikParserError(DalvikParserErrorType.EMPTY_CFG));
         }

         try {
            SegmentMap var12 = new SegmentMap();

            for (IntegerSegment var14 : this.Rr.values()) {
               var12.add(new IntegerSegment(var14.getBegin() - var2, var14.getSize()));
            }

            if (var11.q != null) {
               for (BasicBlock var25 : var11.q) {
                  int var15 = (int)var25.getFirstAddress();
                  int var16 = (int)var25.getEndAddress() - var15;
                  var12.add(new IntegerSegment(var15, var16));
               }
            }

            for (IntegerSegment var27 : var12.generateGapItems(0, true, var3, true, new bij(this), false)) {
               byte[] var28 = Arrays.copyOfRange(var1, var2 + var27.getBegin(), var2 + var27.getEnd());
               var11.RF.put(var27.getBegin(), var28);
            }
         } catch (Exception var20) {
            this.mI.add(new DalvikParserError(DalvikParserErrorType.GAPS_ERROR));
         }

         var22 = var11;
      } finally {
         this.Xo = this.Xo + (System.nanoTime() - var9);
      }

      return var22;
   }

   private CFG q(int var1, biw[] var2) {
      BasicBlockBuilder var3 = null;
      ArrayList var4 = new ArrayList();
      HashMap var5 = new HashMap();
      TreeMap var6 = new TreeMap();
      ArrayDeque var7 = new ArrayDeque();
      var7.push(this.Me);
      boolean var8 = true;
      int var9 = 0;

      while (true) {
         if (var8) {
            if (var7.isEmpty()) {
               if (var2 == null) {
                  break;
               }

               if (var9 == 0) {
                  Set var26 = q(var2);
                  var7.addAll(var26);
               } else {
                  if (var9 != 1) {
                     break;
                  }

                  for (biw var44 : var2) {
                     boolean var47 = false;
                     int var50 = var44.getTryAddress();
                     if (var50 < this.Dz) {
                        Integer var16 = (Integer)var6.floorKey(var50);
                        if (var16 != null && var16 == var50) {
                           var7.add(var50);
                           var47 = true;
                        }
                     }

                     if (!var47) {
                        this.mI.add(new DalvikParserError(DalvikParserErrorType.BAD_TRY_BLOCK_START_ADDRESS, var50));
                     }

                     boolean var53 = false;
                     int var17 = var44.RF();
                     if (var17 <= this.Dz) {
                        Integer var18 = (Integer)var6.lowerKey(var17);
                        if (var18 != null) {
                           bie var19 = (bie)var6.get(var18);
                           int var20 = (int)var19.getOffsetEnd();
                           if (var20 == var17) {
                              if (var17 < this.Dz && var6.get(var17) != null) {
                                 var7.add(var17);
                              }

                              var53 = true;
                           } else if (var20 > var17 && var20 <= this.Dz) {
                              var44.RF(var20);
                              if (var20 < this.Dz && var6.get(var20) != null) {
                                 var7.add(var20);
                              }

                              var53 = true;
                           }
                        }
                     }

                     if (!var53) {
                        this.mI.add(new DalvikParserError(DalvikParserErrorType.BAD_TRY_BLOCK_END_ADDRESS, var17));
                     }
                  }
               }

               var8 = true;
               var9++;
               continue;
            }

            this.Me = (Integer)var7.pop();
            this.Hk = this.io + this.Me;
            var3 = (BasicBlockBuilder)var5.get(this.Me);
            if (var3 != null) {
               if (((bie)var3.insns.get(0)).getOffset() == this.Me) {
                  continue;
               }

               int var25 = -1;
               int var30 = 0;

               for (bie var41 : var3.insns) {
                  if (var41.getOffset() == this.Me) {
                     var25 = var30;
                     break;
                  }

                  var30++;
               }

               if (var25 < 0) {
                  this.mI.add(new DalvikParserError(DalvikParserErrorType.OVERLAPPING_INSTRUCTIONS, this.Me));
                  var8 = true;
                  continue;
               }

               BasicBlockBuilder var37 = new BasicBlockBuilder();
               var4.add(var37);

               for (int var31 = var25; var31 < var3.insns.size(); var31++) {
                  bie var42 = (bie)var3.insns.get(var31);
                  var37.insns.add(var42);
                  var5.put((int)var42.getOffset(), var37);
               }

               var37.dst_offsets = new ArrayList(var3.dst_offsets);
               int var43 = var3.insns.size() - var25;

               for (int var32 = 0; var32 < var43; var32++) {
                  var3.insns.remove(var25);
               }

               var3.dst_offsets.clear();
               var3.dst_offsets.add((long)this.Me);
               continue;
            }

            var3 = new BasicBlockBuilder();
            var4.add(var3);
            var8 = false;
         }

         BasicBlockBuilder var10 = (BasicBlockBuilder)var5.get(this.Me);
         if (var10 != null) {
            if (var3.insns.isEmpty()) {
               throw new RuntimeException("Unexpected empty basic block");
            }

            var3.dst_offsets.add(((bie)var10.insns.get(0)).getOffset());
            var8 = true;
         } else {
            bie var11 = this.q();
            if (this.CE != null) {
               this.mI.add(new DalvikParserError(this.CE, this.Hk - this.io));
               this.q(this.CE);
               if (var11 == null) {
                  var8 = true;
                  continue;
               }
            }

            var3.insns.add(var11);
            var5.put(this.Me, var3);
            var6.put(this.Me, var11);
            IFlowInformation var12 = var11.getBreakingFlow();
            if (!var12.isBroken()) {
               this.Hk = this.Hk + var11.getSize();
               this.Me = this.Me + var11.getSize();
            } else {
               for (ICodePointer var14 : var12.getTargets()) {
                  long var15 = var14.getAddress();
                  if (var15 >= 0L && var15 < this.Dz) {
                     var3.dst_offsets.add(var15);
                     var7.push((int)var15);
                  }
               }

               var8 = true;
            }
         }
      }

      if (var2 != null) {
         for (biw var45 : var2) {
            int var48 = var45.getTryAddress();
            int var51 = var45.RF();
            List var54 = var45.nf();

            for (BasicBlockBuilder var58 : var4) {
               if (!var58.insns.isEmpty()) {
                  int var60 = (int)((bie)var58.insns.get(0)).getOffset();
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
            for (bie var46 : var35.insns) {
               for (big var57 : var46.RF()) {
                  int var59 = var57.getType();
                  if (var59 == 0) {
                     int var61 = (int)var57.getValue();
                     if (var61 < 0 || var61 >= var1) {
                        this.mI.add(new DalvikParserError(DalvikParserErrorType.REGISTER_OUTSIDE_FRAME));
                        break label164;
                     }
                  } else if (var59 == 4) {
                     int var62 = (int)var57.getValue();
                     int var64 = (int)(var57.getValue() >> 32);
                     if (var62 < 0 || var62 > var64 || var64 >= var1) {
                        this.mI.add(new DalvikParserError(DalvikParserErrorType.REGISTER_OUTSIDE_FRAME));
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

   private void q(DalvikParserErrorType var1) {
      if (this.sH != null) {
         this.sH.q(var1, this.io, this.Dz, this.Hk - this.io, this.jq, this.wF == null ? null : this.wF.q);
      } else {
         String var2 = Strings.ff(S.L("Offset %Xh (method=%Xh/%Xh): Parsing error (%s)"), this.Hk, this.io, this.Dz, var1);
         if (this.wF != null && this.wF.q != null) {
            var2 = var2 + Strings.ff(" (%s)", this.wF.q.xK);
         }

         if (this.cC == null) {
            lm.error(var2);
         } else {
            this.cC.logError(true, var2);
         }
      }
   }

   public bie q() {
      this.CE = null;
      this.wF = null;
      if (this.Hk >= this.io && this.Hk < this.qa) {
         if (this.Rr != null && this.Rr.getSegmentContaining(this.Hk) != null) {
            this.CE = DalvikParserErrorType.OUT_OF_BOUNDARIES;
            return null;
         } else {
            int var1 = this.Ef.get(this.Hk) & 255;
            bif.eo var2 = bif.KT[var1];
            byte var3 = 0;
            if (var1 == 255 && this.LK && this.Gf < 50) {
               if (this.Hk + 1 >= this.qa) {
                  this.CE = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                  return null;
               }

               int var4 = this.Ef.get(this.Hk + 1) & 255;
               if (var4 >= bif.wF.length) {
                  this.CE = DalvikParserErrorType.INVALID_EXTENDED_OPCODE;
                  return null;
               }

               var1 = this.Ef.getShort(this.Hk) & '\uffff';
               var2 = bif.wF[var4];
               var3 = 2;
            }

            if (this.TX < var2.nf || var2.xK == null || var2.xK.length() == 0) {
               int var24 = var1 - 227;
               if (this.Gf < 50 && var24 >= 0 && var24 < bif.Ef.length && bif.Ef[var24] != null) {
                  if (!this.PV) {
                     this.CE = DalvikParserErrorType.OPTIMIZED_OPCODE;
                     return null;
                  }

                  var2 = bif.Ef[var24];
                  var3 = 1;
               } else {
                  var24 = var1 - 115;
                  if (this.Gf >= 50 && var24 >= 0 && var24 < bif.sH.length && bif.sH[var24] != null) {
                     if (!this.PV) {
                        this.CE = DalvikParserErrorType.OPTIMIZED_OPCODE;
                        return null;
                     }

                     var2 = bif.sH[var24];
                     var3 = 3;
                  } else {
                     this.CE = DalvikParserErrorType.RESERVED_OPCODE;
                  }
               }
            }

            if (var2.q == 254 || var2.q == 255) {
               this.za++;
            }

            int var26 = 2 * q(var2);
            if (this.Hk + var26 > this.Hk && this.Hk + var26 <= this.qa) {
               bie var5 = new bie();
               var5.q = this.Hk - this.io;
               long var6 = System.nanoTime();
               bid var8 = (bid)this.EB.get(this.Ef.array(), this.Ef.arrayOffset() + this.Hk, this.Ef.arrayOffset() + this.qa, false);
               this.Bu = this.Bu + (System.nanoTime() - var6);
               if (var8 == null || var8.RF() || this.ui != null && var8.xK()) {
                  var8 = new bid();
                  var5.RF = var8;
                  var8.RF = ByteBufferUtil.getBytes(this.Ef, this.Hk, var26);
                  var8.q = var2;
                  var8.gO = Integer.valueOf(var3);
                  this.wF = var8;
                  int var9 = this.Ef.get(this.Hk + 1) & 255;
                  ArrayList var10 = new ArrayList(3);
                  if (q(var2, "10x")) {
                     if (var2.xK == null || var2.xK.length() == 0) {
                        this.CE = DalvikParserErrorType.RESERVED_OPCODE;
                     }
                  } else if (q(var2, "12x", "11n")) {
                     int var11 = var9 & 15;
                     int var12 = var9 >> 4 & 15;
                     var10.add(this.oQ.q(0, var11));
                     if (q(var2, "12x")) {
                        var10.add(this.oQ.q(0, var12));
                     } else if (q(var2, "11n")) {
                        long var13 = (var12 & 8) == 0 ? var12 : var12 | -16L;
                        var10.add(this.oQ.q(1, var13));
                     }
                  } else if (q(var2, "11x", "10t")) {
                     if (q(var2, "11x")) {
                        var10.add(this.oQ.q(0, var9));
                     } else if (q(var2, "10t")) {
                        byte var29 = this.Ef.get(this.Hk + 1);
                        var10.add(this.oQ.q(3, var29));
                     }
                  } else if (q(var2, "20t")) {
                     if (var9 != 0) {
                        this.CE = DalvikParserErrorType.INVALID_OPCODE_FORMAT;
                     }

                     short var30 = this.Ef.getShort(this.Hk + 2);
                     var10.add(this.oQ.q(3, var30));
                  } else if (q(var2, "20bc")) {
                     int var31 = this.Ef.get(this.Hk + 1) & 255;
                     long var49 = this.Ef.getShort(this.Hk + 2) & '\uffff';
                     var10.add(this.oQ.q(1, var31));
                     var10.add(this.oQ.q(1, var49));
                  } else if (q(var2, "22x", "21t", "21s", "21h", "21c")) {
                     int var32 = this.Ef.get(this.Hk + 1) & 255;
                     long var50 = this.Ef.getShort(this.Hk + 2);
                     var10.add(this.oQ.q(0, var32));
                     if (q(var2, "22x")) {
                        var10.add(this.oQ.q(0, var50 & 65535L));
                     } else if (q(var2, "21t")) {
                        var10.add(this.oQ.q(3, var50));
                     } else if (q(var2, "21s")) {
                        var10.add(this.oQ.q(1, var50));
                     } else if (q(var2, "21h")) {
                        if (var1 == 21) {
                           var10.add(this.oQ.q(1, var50 << 16));
                        } else {
                           var10.add(this.oQ.q(1, var50 << 48));
                        }
                     } else {
                        var10.add(this.oQ.q(2, this.q(var2, (int)(var50 & 65535L))));
                     }
                  } else if (q(var2, "23x", "22b")) {
                     int var33 = this.Ef.get(this.Hk + 1) & 255;
                     int var51 = this.Ef.get(this.Hk + 2) & 255;
                     var10.add(this.oQ.q(0, var33));
                     var10.add(this.oQ.q(0, var51));
                     if (q(var2, "23x")) {
                        int var66 = this.Ef.get(this.Hk + 3) & 255;
                        var10.add(this.oQ.q(0, var66));
                     } else {
                        byte var67 = this.Ef.get(this.Hk + 3);
                        var10.add(this.oQ.q(1, var67));
                     }
                  } else if (q(var2, "22t", "22s", "22c", "22cs")) {
                     int var34 = this.Ef.get(this.Hk + 1) & 15;
                     int var52 = this.Ef.get(this.Hk + 1) >> 4 & 15;
                     var10.add(this.oQ.q(0, var34));
                     var10.add(this.oQ.q(0, var52));
                     short var68 = this.Ef.getShort(this.Hk + 2);
                     if (q(var2, "22t")) {
                        if (var34 == var52 && !this.gP) {
                           var10.clear();
                           var8.q = bif.KT[41];
                           var8.gO = 0;
                           var8.RF[0] = 41;
                           var8.RF[1] = 0;
                           if (var1 != 50) {
                              var8.RF[2] = 2;
                              var8.RF[3] = 0;
                              var68 = 2;
                           }
                        }

                        var10.add(this.oQ.q(3, var68));
                     } else if (q(var2, "22s")) {
                        var10.add(this.oQ.q(1, var68));
                     } else if (q(var2, "22c")) {
                        var10.add(this.oQ.q(2, this.q(var2, var68 & '\uffff')));
                     } else {
                        if (!q(var2, "22cs")) {
                           this.CE = DalvikParserErrorType.UNIMPLEMENTED_ENCODING_FORMAT;
                           return null;
                        }

                        var10.add(this.oQ.q(2, this.q(var2, var68 & '\uffff')));
                     }
                  } else if (q(var2, "30t")) {
                     if (this.Ef.get(this.Hk + 1) != 0) {
                        this.CE = DalvikParserErrorType.INVALID_OPCODE_FORMAT;
                     }

                     int var35 = this.Ef.getInt(this.Hk + 2);
                     var10.add(this.oQ.q(3, var35));
                  } else if (q(var2, "32x")) {
                     if (this.Ef.get(this.Hk + 1) != 0) {
                        this.CE = DalvikParserErrorType.INVALID_OPCODE_FORMAT;
                     }

                     int var36 = this.Ef.getShort(this.Hk + 2) & '\uffff';
                     int var53 = this.Ef.getShort(this.Hk + 4) & '\uffff';
                     var10.add(this.oQ.q(0, var36));
                     var10.add(this.oQ.q(0, var53));
                  } else if (q(var2, "31i", "31t", "31c")) {
                     int var37 = this.Ef.get(this.Hk + 1) & 255;
                     int var54 = this.Ef.getInt(this.Hk + 2);
                     var10.add(this.oQ.q(0, var37));
                     if (q(var2, "31i")) {
                        var10.add(this.oQ.q(1, var54));
                     } else if (q(var2, "31t")) {
                        int var69 = this.Hk + 2 * var54;
                        if (var69 < this.io || var69 > this.qa) {
                           this.CE = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                           return null;
                        }

                        byte var14 = 0;

                        while (var69 + var14 + 2 <= this.qa && this.Ef.getShort(var69 + var14) == 0) {
                           var14 += 2;
                        }

                        if (var14 != 0) {
                           this.q(DalvikParserErrorType.PADDING_BEFORE_PSEUDO_OPCODE);
                        }

                        int var15 = var69 + var14;
                        int var16 = var14 / 2;
                        var10.add(this.oQ.q(3, var54 + var16));
                        if (var2.xK.equals("packed-switch")) {
                           int var17 = var15 + 8;
                           if (var17 < var15 || var17 > this.qa) {
                              this.CE = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                              return null;
                           }

                           if (this.Ef.getShort(var15) != 256) {
                              this.CE = DalvikParserErrorType.INVALID_PSEUDO_INSTRUCTION_ID;
                              return null;
                           }

                           int var18 = this.Ef.getShort(var15 + 2) & '\uffff';
                           int var19 = this.Ef.getInt(var15 + 4);
                           int var20 = 8 + 4 * var18;
                           var17 = var15 + var20;
                           if (var17 < var15 || var17 > this.qa) {
                              this.CE = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                              return null;
                           }

                           bis var21 = new bis(var15 - this.io, var18);

                           for (int var22 = 0; var22 < var18; var22++) {
                              int var23 = this.Ef.getInt(var15 + 8 + 4 * var22);
                              var21.RF[var22] = new bis.eo(var19 + var22, var23);
                           }

                           var8.Dw = var21;
                           IntegerSegment var117 = (IntegerSegment)this.Rr.get(var15);
                           if (var117 != null) {
                              Assert.a(var117.getSize() == var20);
                           } else {
                              this.Rr.add(new IntegerSegment(var15, var20));
                           }
                        } else if (var2.xK.equals("sparse-switch")) {
                           int var95 = var15 + 4;
                           if (var95 < var15 || var95 > this.qa) {
                              this.CE = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                              return null;
                           }

                           if (this.Ef.getShort(var15) != 512) {
                              this.CE = DalvikParserErrorType.INVALID_PSEUDO_INSTRUCTION_ID;
                              return null;
                           }

                           int var103 = this.Ef.getShort(var15 + 2) & '\uffff';
                           int var107 = 4 + 8 * var103;
                           var95 = var15 + var107;
                           if (var95 < var15 || var95 > this.qa) {
                              this.CE = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                              return null;
                           }

                           bis var110 = new bis(var15 - this.io, var103);
                           int[] var114 = new int[var103];

                           for (int var118 = 0; var118 < var103; var118++) {
                              var114[var118] = this.Ef.getInt(var15 + 4 + 4 * var118);
                           }

                           for (int var119 = 0; var119 < var103; var119++) {
                              int var123 = this.Ef.getInt(var15 + 4 + 4 * var103 + 4 * var119);
                              var110.RF[var119] = new bis.eo(var114[var119], var123);
                           }

                           var8.Dw = var110;
                           IntegerSegment var120 = (IntegerSegment)this.Rr.get(var15);
                           if (var120 != null) {
                              Assert.a(var120.getSize() == var107);
                           } else {
                              this.Rr.add(new IntegerSegment(var15, var107));
                           }
                        } else if (var2.xK.equals("fill-array-data")) {
                           int var97 = var15 + 8;
                           if (var97 < var15 || var97 > this.qa) {
                              this.CE = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                              return null;
                           }

                           if (this.Ef.getShort(var15) != 768) {
                              this.CE = DalvikParserErrorType.INVALID_PSEUDO_INSTRUCTION_ID;
                              return null;
                           }

                           int var104 = this.Ef.getShort(var15 + 2) & '\uffff';
                           int var108 = this.Ef.getInt(var15 + 4);
                           int var111 = 2 * ((var108 * var104 + 1) / 2 + 4);
                           var97 = var15 + var111;
                           if (var97 < var15 || var97 > this.qa) {
                              this.CE = DalvikParserErrorType.OUT_OF_BOUNDARIES;
                              return null;
                           }

                           bib var115 = new bib(var15 - this.io, var108, var104);

                           for (int var121 = 0; var121 < var108; var121++) {
                              for (int var124 = 0; var124 < var104; var124++) {
                                 var115.RF[var121][var124] = this.Ef.get(var15 + 8 + var121 * var104 + var124);
                              }
                           }

                           var8.Uv = var115;
                           IntegerSegment var122 = (IntegerSegment)this.Rr.get(var15);
                           if (var122 != null) {
                              Assert.a(var122.getSize() == var111);
                           } else {
                              this.Rr.add(new IntegerSegment(var15, var111));
                           }
                        }
                     } else {
                        var10.add(this.oQ.q(2, this.q(var2, var54)));
                     }
                  } else if (q(var2, "35c", "35ms", "35mi", "45cc")) {
                     int var38 = var9 >> 4;
                     if (var38 > 5) {
                        this.CE = DalvikParserErrorType.INVALID_ARGUMENT_COUNT;
                        var38 = 5;
                     }

                     int var55 = this.Ef.getShort(this.Hk + 2) & '\uffff';
                     int[] var70 = new int[]{0, 0, 0, 0, 0};
                     short var79 = this.Ef.getShort(this.Hk + 4);
                     var70[0] = var79 & 15;
                     var70[1] = var79 >> 4 & 15;
                     var70[2] = var79 >> 8 & 15;
                     var70[3] = var79 >> 12 & 15;
                     var70[4] = var9 & 15;
                     var10.add(this.oQ.q(2, this.q(var2, var55)));
                     if (q(var2, "45cc")) {
                        int var87 = this.Ef.getShort(this.Hk + 6) & '\uffff';
                        var10.add(this.oQ.q(5, this.RF(var2, var87)));
                     }

                     for (int var88 = 0; var88 < var38; var88++) {
                        var10.add(this.oQ.q(0, var70[var88]));
                     }
                  } else if (q(var2, "3rc", "3rms", "3rmi", "4rcc")) {
                     int var39 = this.Ef.get(this.Hk + 1) & 255;
                     int var56 = this.Ef.getShort(this.Hk + 2) & '\uffff';
                     int var71 = this.Ef.getShort(this.Hk + 4) & '\uffff';
                     var10.add(this.oQ.q(2, this.q(var2, var56)));
                     if (q(var2, "4rcc")) {
                        int var80 = this.Ef.getShort(this.Hk + 6) & '\uffff';
                        var10.add(this.oQ.q(5, this.RF(var2, var80)));
                     }

                     long var81 = var71 | (long)(var71 + var39 - 1) << 32;
                     var10.add(this.oQ.q(4, var81));
                  } else if (q(var2, "51l")) {
                     int var40 = this.Ef.get(this.Hk + 1) & 255;
                     long var57 = this.Ef.getLong(this.Hk + 2);
                     var10.add(this.oQ.q(0, var40));
                     var10.add(this.oQ.q(1, var57));
                  } else if (q(var2, "41c")) {
                     int var41 = this.Ef.getInt(this.Hk + 2);
                     int var58 = this.Ef.getShort(this.Hk + 6) & '\uffff';
                     var10.add(this.oQ.q(0, var58));
                     var10.add(this.oQ.q(2, this.q(var2, var41)));
                  } else if (q(var2, "52c")) {
                     int var42 = this.Ef.getInt(this.Hk + 2);
                     int var59 = this.Ef.getShort(this.Hk + 6) & '\uffff';
                     int var72 = this.Ef.getShort(this.Hk + 8) & '\uffff';
                     var10.add(this.oQ.q(0, var59));
                     var10.add(this.oQ.q(0, var72));
                     var10.add(this.oQ.q(2, this.q(var2, var42)));
                  } else {
                     if (!q(var2, "5rc")) {
                        this.CE = DalvikParserErrorType.UNIMPLEMENTED_ENCODING_FORMAT;
                        return null;
                     }

                     int var43 = this.Ef.getInt(this.Hk + 2);
                     int var60 = this.Ef.getShort(this.Hk + 6) & '\uffff';
                     int var73 = this.Ef.getShort(this.Hk + 8) & '\uffff';
                     var10.add(this.oQ.q(2, this.q(var2, var43)));
                     long var82 = var73 | (long)(var73 + var60 - 1) << 32;
                     var10.add(this.oQ.q(4, var82));
                  }

                  if (this.HF != null && var3 == 0) {
                     for (big var61 : var10) {
                        int var74 = var61.getType();
                        if (var74 == 2 || var74 == 5) {
                           int var83 = (int)var61.getValue();
                           if (var83 < 0) {
                              this.CE = DalvikParserErrorType.INVALID_INDEX;
                              break;
                           }

                           int var89;
                           if (var74 == 2) {
                              var89 = var2.Uv & 15;
                           } else {
                              var89 = var2.Uv >> 8 & 15;
                           }
                           if (var83 >= switch (var89) {
                              case 1 -> this.HF.q;
                              case 2 -> this.HF.RF;
                              case 3 -> this.HF.xK;
                              case 4 -> this.HF.Dw;
                              case 5 -> this.HF.Uv;
                              case 6 -> this.HF.oW;
                              default -> throw new RuntimeException("Unknown index type for instruction: " + var89);
                              case 10 -> this.HF.gO;
                           }) {
                              this.CE = DalvikParserErrorType.INVALID_INDEX;
                           }
                        }
                     }
                  }

                  var8.xK = new big[var10.size()];
                  var10.toArray(var8.xK);
                  this.EB.put(var8.RF, var8);
                  if (this.zz != null) {
                     for (big var62 : var10) {
                        if (var62.getType() == 1 && !this.zz.containsKey(var62.getValue())) {
                           long var75 = var62.getValue();
                           this.zz.put(var75, var75);
                        }
                     }

                     if (var5.isSwitch()) {
                        for (bis.eo var84 : var5.Dw()) {
                           long var90 = var84.q() & 4294967295L;
                           this.zz.put(var90, var90);
                        }
                     }

                     if (var5.isArray()) {
                        for (long var85 : var5.Uv().asArrayOfLongs()) {
                           this.zz.put(var85, var85);
                        }
                     }
                  }
               } else {
                  var5.RF = var8;
                  this.KT++;
               }

               this.xW++;
               if (this.JY != null && var3 == 0) {
                  for (big var65 : var8.xK) {
                     int var78 = var65.getType();
                     if (var78 == 2 || var78 == 5) {
                        int var86 = (int)var65.getValue();
                        if (var86 >= 0) {
                           int var91;
                           if (var78 == 2) {
                              var91 = var2.Uv & 15;
                           } else {
                              var91 = var2.Uv >> 8 & 15;
                           }

                           int var93 = var2.Uv & 0xFF0000;
                           switch (var91) {
                              case 1:
                                 if (this.HF == null || var86 < this.HF.q) {
                                    this.JY.q(var86, this.jq, this.Me, com.pnfsoftware.jeb.corei.parsers.dex.iZ.RF);
                                 }
                                 break;
                              case 2:
                                 if (this.HF != null && var86 >= this.HF.RF) {
                                    break;
                                 }

                                 int var102 = 0;
                                 if ((var93 & 262144) != 0) {
                                    var102 = com.pnfsoftware.jeb.corei.parsers.dex.iZ.za;
                                 }

                                 this.JY.RF(var86, this.jq, this.Me, var102);
                                 break;
                              case 3:
                                 if (this.HF != null && var86 >= this.HF.xK) {
                                    break;
                                 }

                                 int var101 = 0;
                                 if ((var93 & 131072) != 0) {
                                    var101 = com.pnfsoftware.jeb.corei.parsers.dex.iZ.Uv;
                                 } else if ((var93 & 65536) != 0) {
                                    var101 = com.pnfsoftware.jeb.corei.parsers.dex.iZ.Dw;
                                 }

                                 this.JY.xK(var86, this.jq, this.Me, var101);
                                 break;
                              case 4:
                                 if (this.HF == null || var86 < this.HF.Dw) {
                                    this.JY.Dw(var86, this.jq, this.Me, com.pnfsoftware.jeb.corei.parsers.dex.iZ.oW);
                                 }
                              case 5:
                                 break;
                              case 6:
                                 if (this.cC == null || var86 >= this.cC.getCallSites().size()) {
                                    break;
                                 }

                                 bjm var100 = this.cC.gP(var86);

                                 for (IDexValue var109 : var100.getCallSiteValues()) {
                                    if (var109.getType() == 23) {
                                       int var112 = var109.getStringIndex();
                                       this.JY.q(var112, this.jq, this.Me, com.pnfsoftware.jeb.corei.parsers.dex.iZ.xK);
                                    } else if (var109.getType() == 22) {
                                       bjq var113 = this.cC.za(var109.getMethodHandleIndex());
                                       if (var113.getMethodHandleType().isMethodInvoker()) {
                                          this.JY.Dw(var113.getFieldOrMethodIndex(), this.jq, this.Me, com.pnfsoftware.jeb.corei.parsers.dex.iZ.gP);
                                       } else {
                                          int var116 = 0;
                                          if (var113.getMethodHandleType().isFieldSetter()) {
                                             var116 = com.pnfsoftware.jeb.corei.parsers.dex.iZ.nf;
                                          } else if (var113.getMethodHandleType().isFieldGetter()) {
                                             var116 = com.pnfsoftware.jeb.corei.parsers.dex.iZ.gO;
                                          }

                                          this.JY.xK(var113.getFieldOrMethodIndex(), this.jq, this.Me, var116);
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
                                 if (this.cC != null && var86 < this.cC.getMethodHandles().size()) {
                                    bjq var99 = this.cC.za(var86);
                                    if (var99.getMethodHandleType().isMethodInvoker()) {
                                       this.JY.Dw(var99.getFieldOrMethodIndex(), this.jq, this.Me, com.pnfsoftware.jeb.corei.parsers.dex.iZ.gP);
                                    } else {
                                       int var105 = 0;
                                       if (var99.getMethodHandleType().isFieldSetter()) {
                                          var105 = com.pnfsoftware.jeb.corei.parsers.dex.iZ.nf;
                                       } else if (var99.getMethodHandleType().isFieldGetter()) {
                                          var105 = com.pnfsoftware.jeb.corei.parsers.dex.iZ.gO;
                                       }

                                       this.JY.xK(var99.getFieldOrMethodIndex(), this.jq, this.Me, var105);
                                    }
                                 }
                           }
                        }
                     }
                  }
               }

               return var5;
            } else {
               this.CE = DalvikParserErrorType.OUT_OF_BOUNDARIES;
               return null;
            }
         }
      } else {
         this.CE = DalvikParserErrorType.OUT_OF_BOUNDARIES;
         return null;
      }
   }

   public long RF() {
      return this.xW;
   }

   private long q(bif.eo var1, int var2) {
      return this.q(var1.Uv & 15, var2);
   }

   private long RF(bif.eo var1, int var2) {
      return this.q(var1.Uv >> 8 & 15, var2);
   }

   private long q(int var1, int var2) {
      if (this.ui == null) {
         return var2;
      } else {
         switch (var1) {
            case 1:
               return this.ui.q(var2);
            case 2:
               return this.ui.RF(var2);
            case 3:
               return this.ui.Dw(var2);
            case 4:
               return this.ui.Uv(var2);
            case 5:
               return this.ui.xK(var2);
            case 6:
               return this.ui.oW(var2);
            case 7:
            case 8:
            case 9:
            default:
               return var2;
            case 10:
               return this.ui.gO(var2);
         }
      }
   }

   private static boolean q(bif.eo var0, String var1) {
      return var0.RF.equals(var1);
   }

   private static boolean q(bif.eo var0, String var1, String var2) {
      return var0.RF.equals(var1) || var0.RF.equals(var2);
   }

   private static boolean q(bif.eo var0, String var1, String var2, String var3) {
      return var0.RF.equals(var1) || var0.RF.equals(var2) || var0.RF.equals(var3);
   }

   private static boolean q(bif.eo var0, String var1, String var2, String var3, String var4) {
      return var0.RF.equals(var1) || var0.RF.equals(var2) || var0.RF.equals(var3) || var0.RF.equals(var4);
   }

   private static boolean q(bif.eo var0, String var1, String var2, String var3, String var4, String var5) {
      return var0.RF.equals(var1) || var0.RF.equals(var2) || var0.RF.equals(var3) || var0.RF.equals(var4) || var0.RF.equals(var5);
   }

   private static int q(bif.eo var0) {
      if (var0.RF.length() != 3 && var0.RF.length() != 4) {
         throw new RuntimeException("Invalid instruction format for definition: " + var0);
      } else {
         return var0.RF.codePointAt(0) - 48;
      }
   }

   private static List q(biw[] var0, int var1) {
      HashSet var2 = new HashSet();

      for (biw var6 : var0) {
         int var7 = var6.getTryAddress();
         if (var7 < var1) {
            var2.add(var7);
         }

         var7 = var6.RF();
         if (var7 < var1) {
            var2.add(var7);
         }
      }

      return new ArrayList(var2);
   }

   private static Set q(biw[] var0) {
      LinkedHashSet var1 = new LinkedHashSet();

      for (biw var5 : var0) {
         var1.addAll(var5.nf());
      }

      return var1;
   }

   public String xK() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "insnCount= %d\n", this.xW);
      Strings.ff(var1, "insnDup= %d\n", this.KT);
      Strings.ff(var1, "totalParsingTime= %s\n", TimeFormatter.formatExecutionTime(this.Xo / 1000000L));
      Strings.ff(var1, "insnCacheRetrievalTime= %s", TimeFormatter.formatExecutionTime(this.Bu / 1000000L));
      return var1.toString();
   }

   public static String RF(int var0) {
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

   @Ser
   @SerVersion(2)
   public static class CU {
      @SerId(1)
      int q;
      @SerId(2)
      int RF;
      @SerId(3)
      int xK;
      @SerId(4)
      int Dw;
      @SerId(value = 5, version = 1)
      int Uv;
      @SerId(value = 6, version = 1)
      int oW;
      @SerId(value = 7, version = 2)
      int gO;

      public CU(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
         this.Uv = var5;
         this.oW = var6;
         this.gO = var7;
      }
   }

   public static class eo {
      public CFG q;
      public Map RF = new TreeMap();
      public List xK;
   }
}
