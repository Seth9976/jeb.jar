package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.CoordinatesConversionPrecision;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.AssemblyItem;
import com.pnfsoftware.jeb.core.output.code.CodeAnchor;
import com.pnfsoftware.jeb.core.output.code.CodeDocument;
import com.pnfsoftware.jeb.core.output.code.CodeLine;
import com.pnfsoftware.jeb.core.output.code.CommentGenerator;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.NativeCoordinates;
import com.pnfsoftware.jeb.core.output.text.IBinaryRepresentation;
import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import com.pnfsoftware.jeb.core.output.text.impl.Coordinates;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionGroup;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeString;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMemoryItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.INativeDisassemblyDocument;
import com.pnfsoftware.jeb.core.units.code.asm.render.NativeDisassemblyProperties;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeAttribute;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.core.units.impl.Comment;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.AddressSegmentMap;
import com.pnfsoftware.jeb.util.collect.ISegment;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.primitives.Characters;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class bay extends CodeDocument implements INativeDisassemblyDocument, baw {
   private static final ILogger GY = GlobalLog.getLogger(bay.class);
   private boolean Wx;
   private IPropertyManager AB;
   private abg CY;
   private IVirtualMemory WI;
   private MemoryRanges Tq;
   private GenericCodeFormatter Yp;
   private aaf Gu;
   private IEventListener nY;
   private IEventListener lF;
   private static final int nq = 9;
   private static final String NX = "SLACK";
   private int br = "SLACK".length();
   private AddressSegmentMap tW;
   private AddressSegmentMap ZA;
   private NativeDisassemblyProperties Ov;
   private boolean Lj = true;
   private int nv = 0;
   private boolean LL = true;
   private String PQ = ".";
   private int fQ = 80;
   private boolean fi = true;
   private boolean bl = false;
   private boolean jb = true;
   private String pQ = "=";
   private String kf = "-";
   private int GM = 80;
   private boolean TQ = false;
   private boolean Yw = true;
   private int IY = 16;
   private int qR = 40;
   private int YA = 50;
   private boolean fw = false;
   private boolean Wp = true;
   private boolean cY = false;
   private int PY = 16;
   private int cR = 1;
   private String eC = "'";
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 3;
   private String ND;
   private String Qu;
   private String jh;
   private int Jf = 100;
   private int vC = 100;
   public static final String Dw = "ShowBytesCount";
   public static final String Uv = "ShowAddresses";
   public static final String oW = "ShowSegmentHeaders";
   public static final String gO = "SegmentHeadersSeparator";
   public static final String nf = "SegmentHeadersSeparatorLength";
   public static final String gP = "SegmentHeadersSeparatorLengthABL";
   public static final String za = "SegmentHeadersIndentABL";
   public static final String lm = "ShowRoutineHeaders";
   public static final String zz = "RoutineHeadersSeparator";
   public static final String JY = "RoutineEndSeparator";
   public static final String HF = "RoutineSeparatorLength";
   public static final String LK = "RoutineHeadersSeparatorLengthABL";
   public static final String io = "RoutineHeadersIndentABL";
   public static final String qa = "ShowSpaceBetweenBlocks";
   public static final String Hk = "Hide0Padding";
   public static final String Me = "LabelAreaLength";
   public static final String PV = "InstructionAreaLength";
   public static final String oQ = "BlockXrefsCount";
   public static final String xW = "GapPreferRawFormatting";
   public static final String KT = "GapRawBytesPerLine";
   public static final String Gf = "GapRawIntegerSize";
   public static final String Ef = "CharBreak64BitAddresses";
   public static final int cC = 0;
   public static final boolean sH = true;
   public static final boolean CE = true;
   public static final String wF = ".";
   public static final int If = 80;
   public static final boolean Dz = true;
   public static final boolean mI = false;
   public static final boolean jq = true;
   public static final String ui = "=";
   public static final String TX = "-";
   public static final int Rr = 80;
   public static final boolean EB = false;
   public static final boolean Xo = true;
   public static final boolean Bu = false;
   public static final boolean IN = true;
   public static final int rL = 16;
   public static final int eJ = 40;
   public static final int YN = 50;
   public static final boolean Rv = false;
   public static final int zx = 16;
   public static final int ZT = 1;
   public static final String Ri = "'";

   public bay(abg var1) {
      this.CY = var1;
      this.Yp = var1.getCodeFormatter();
      this.Gu = (aaf)var1.getCodeModel();
      this.tW = new AddressSegmentMap(this.Gu.getBitsize());
      this.ZA = new AddressSegmentMap(this.Gu.getBitsize());
      this.WI = var1.getMemory();
      this.Tq = new MemoryRanges(this.WI);
      this.q(false);
      var1.addListener(this.nY = new baz(this));
      this.AB = var1.getPropertyManager();
      this.AB.addListener(this.lF = new bba(this));
   }

   @Override
   public void dispose() {
      if (!this.Wx) {
         super.dispose();
         this.CY.removeListener(this.nY);
         this.AB.removeListener(this.lF);
         this.Wx = true;
      }
   }

   @Override
   public long getInitialAnchor() {
      if (this.CY.isInitialAnalysisDone()) {
         long var1 = this.CY.getHighLevelEntryPointAddress();
         if (var1 != -1L) {
            return this.q(var1);
         }

         if (this.CY.getParent() instanceof ICodeObjectUnit) {
            ICodeObjectUnit var3 = (ICodeObjectUnit)this.CY.getParent();
            var1 = this.CY.getVirtualImageBase() + var3.getLoaderInformation().getEntryPoint();
            return this.q(var1);
         }
      }

      return super.getInitialAnchor();
   }

   @Override
   public long getAnchorCount() {
      return this.Tq.aggregatedRangesSize();
   }

   public long q(long var1) {
      return this.q(var1, false);
   }

   public long q(long var1, boolean var3) {
      long var4 = 0L;

      for (Couple var7 : this.Tq.asList()) {
         long var8 = (Long)var7.getFirst();
         long var10 = (Long)var7.getSecond();
         if (Longs.compareUnsigned(var1, var8) < 0) {
            return var3 ? var4 : -1L;
         }

         if (Longs.compareUnsigned(var1, var8) >= 0 && Longs.compareUnsigned(var1, var10) < 0) {
            return var4 + (var1 - var8);
         }

         var4 += var10 - var8;
      }

      return var3 ? var4 : -1L;
   }

   public long RF(long var1) {
      long var3 = 0L;

      for (Couple var6 : this.Tq.asList()) {
         long var7 = (Long)var6.getFirst();
         long var9 = (Long)var6.getSecond();
         long var11 = var3 + (var9 - var7);
         if (var1 >= var3 && var1 < var11) {
            return var7 + (var1 - var3);
         }

         var3 = var11;
      }

      return 0L;
   }

   @Override
   public String coordinatesToAddress(ICoordinates var1, AddressConversionPrecision var2) {
      int var3 = var2 == AddressConversionPrecision.COARSE ? 0 : 2;
      long var4 = this.RF(var1.getAnchorId());
      if (this.CY.RF(var4) != null) {
         int var6 = var1.getLineDelta();

         try {
            INativeContinuousItem var7 = this.Gu.getItemAt(var4);
            if (var4 == this.CY.getVirtualImageBase()) {
               bat var8 = new bat();
               this.q(var8);
               var6 -= var8.getLineCount();
            }

            bat var17 = new bat();
            this.q(var17, var4);
            var6 -= var17.getLineCount();
            var6 -= this.RF(var7);
            if (var6 < 0) {
               var3 = 1;
            }
         } catch (Exception var11) {
            GY.catchingSilent(var11);
         }
      } else if (var1.getColumnOffset() != 0 && this.nv > 0) {
         int var14 = var1.getLineDelta();

         try {
            INativeContinuousItem var16 = this.Gu.getItemOverOrGap(var4, var4, var4 + 32L);
            if (var4 == this.CY.getVirtualImageBase()) {
               bat var18 = new bat();
               this.q(var18);
               var14 -= var18.getLineCount();
            }

            bat var19 = new bat();
            this.q(var19, var4);
            var14 -= var19.getLineCount();
            if (var4 == var16.getMemoryAddress()) {
               var14 -= this.RF(var16);
            }

            if (var14 >= 0) {
               int var20 = var1.getColumnOffset() - this.q() + 1;
               if (var20 > 0) {
                  int var9 = var20 / 3;
                  if (var9 < this.nv) {
                     if (var16 instanceof axn) {
                        if (var9 < var16.getMemorySize()) {
                           var4 += var9;
                        }
                     } else if (var16 instanceof axs) {
                        if (var9 < Math.min((long)this.nv, (Long)var16.getEnd() - var4)) {
                           var4 += var9;
                        }
                     } else if (var16 instanceof INativeDataItem && var9 < var16.getMemorySize()) {
                        var4 += var9;
                     }
                  }
               }
            }
         } catch (Exception var10) {
            GY.catchingSilent(var10);
         }
      }

      return this.CY.getSymbolicStringAddress(var4, var3);
   }

   private int q() {
      bat var1 = new bat();
      this.RF(var1, 0L, true);
      return var1.getCurrentLineLength();
   }

   private int RF(INativeContinuousItem var1) {
      if (var1 instanceof axn) {
         bat var4 = new bat();
         this.q(var4, (axn)var1, var1.getMemoryAddress());
         return var4.getLineCount();
      } else if (var1 instanceof axs) {
         bat var3 = new bat();
         return var3.getLineCount();
      } else if (var1 instanceof INativeDataItem) {
         bat var2 = new bat();
         this.q(var2, (INativeDataItem)var1);
         return var2.getLineCount();
      } else {
         return 0;
      }
   }

   @Override
   public ICoordinates addressToCoordinates(String var1, CoordinatesConversionPrecision var2) {
      if (var2 == null) {
         var2 = CoordinatesConversionPrecision.BEST;
      }

      long var3 = this.CY.getCanonicalMemoryAddress(var1);
      if (var3 == -1L) {
         return null;
      } else {
         if (this.CY.getPhysicalImageDelta() != 0L
            && (
               Longs.compareUnsigned(var3, this.CY.getVirtualImageBase()) < 0
                  || Longs.compareUnsigned(var3, this.CY.getVirtualImageBase() + this.CY.getImageSize()) > 0
            )) {
            var3 -= this.CY.getPhysicalImageDelta();
         }

         long var5 = this.q(var3);
         if (var5 == -1L) {
            return null;
         } else {
            INativeContinuousItem var7 = this.Gu.getItemOver(var3);
            if (var7 != null) {
               boolean var8 = var7 instanceof INativeInstructionItem;
               bat var9 = new bat(0L, this);
               this.q(var9, var7, 10, -1L, -1L, -1, -1);
               int var10 = -1;
               int var11 = -1;

               for (int var12 = 0; var12 < var9.getCountOfAnchors(); var12++) {
                  if (var9.getAnchor(var12).getIdentifier() == var5) {
                     var10 = var9.getAnchor(var12).getLineIndex();
                     if (var12 + 1 < var9.getCountOfAnchors()) {
                        var11 = var9.getAnchor(var12 + 1).getLineIndex();
                     } else {
                        var11 = var9.getCountOfLines();
                     }
                     break;
                  }
               }

               if (var10 >= 0) {
                  int var21 = 0;
                  Coordinates var13 = null;

                  for (int var14 = var10; var14 < var11; var14++) {
                     CodeLine var15 = var9.getLine(var14);
                     List var16 = (List)var15.getItems()
                        .stream()
                        .filter(
                           var0 -> var0.getClassId() != ItemClassIdentifiers.COMMENT
                              && var0.getClassId() != ItemClassIdentifiers.COMMENT_AUTO
                              && var0.getClassId() != ItemClassIdentifiers.ADDRESS
                        )
                        .collect(Collectors.toList());
                     if (var16.isEmpty()) {
                        var21++;
                     } else {
                        int var17 = 0;

                        try {
                           var17 = Integer.min((var8 ? this.Qu : this.ND).length(), var15.getText().length());
                        } catch (Exception var20) {
                        }

                        for (AssemblyItem var19 : var16) {
                           if (var19.getOffset() >= var17) {
                              if (var19.getOffset() != var17) {
                                 var17 = var19.getOffset();
                              }
                              break;
                           }
                        }

                        Coordinates var22 = new Coordinates(var5, var21, var17);
                        if (var2 == CoordinatesConversionPrecision.FIRST) {
                           return var22;
                        }

                        if (var2 == CoordinatesConversionPrecision.BEST) {
                           if (!((List)var16.stream().filter(var0 -> var0.getClassId() == ItemClassIdentifiers.MNEMONIC).collect(Collectors.toList()))
                              .isEmpty()) {
                              return var22;
                           }

                           if (var13 == null) {
                              var13 = var22;
                           }
                        } else if (var2 == CoordinatesConversionPrecision.LAST) {
                           var13 = var22;
                        }

                        var21++;
                     }
                  }

                  if (var13 != null) {
                     return var13;
                  }
               }
            }

            return new Coordinates(var5, 0, 0);
         }
      }
   }

   public bat q(long var1, long var3) {
      long var5 = this.Tq.min();
      long var7 = this.Tq.max();
      long var9 = this.Tq.getLocalBegin(var1);
      long var11 = this.Tq.getLocalEnd(var1);
      if (Longs.compareUnsigned(var1, var5) >= 0 && Longs.compareUnsigned(var1, var7) < 0) {
         axg var13 = this.q(var1, var9, var11);
         if (var13 == null) {
            return null;
         } else {
            long var14 = var13.getMemoryAddress();
            bat var16 = new bat(0L, this);
            this.q(var16, var14, var1, -1, -1, var3, var5, var7);
            return var16;
         }
      } else {
         return null;
      }
   }

   public bat q(long var1, int var3, int var4) {
      long var5 = this.Tq.min();
      long var7 = this.Tq.max();
      long var9 = this.RF(var1);
      long var11 = this.Tq.getLocalBegin(var9);
      long var13 = this.Tq.getLocalEnd(var9);
      if (Longs.compareUnsigned(var9, var5) >= 0 && Longs.compareUnsigned(var9, var7) < 0) {
         axg var15 = this.q(var9, var11, var13);
         if (var15 == null) {
            return null;
         } else {
            INativeContinuousItem var16 = this.xK(var9, true);
            if (var16 != null) {
               var9 = var16.getMemoryAddress();
               var15 = (axg)var16;
            }

            Object[] var10000 = new Object[]{var4, var9, var3};
            if (var4 < Integer.MAX_VALUE - this.Jf) {
               var4 += this.Jf;
            }

            if (var3 < Integer.MAX_VALUE - this.vC) {
               var3 += this.vC;
            }

            long var17 = var15.getMemoryAddress();
            bat var19 = new bat(0L, this);
            this.q(var19, var17, var9, var4, var3, -1L, var5, var7);
            int var20 = 0;

            for (CodeAnchor var22 : var19.getAnchors()) {
               if (Longs.compareUnsigned(var22.getIdentifier(), var1) > 0) {
                  var20 = var22.getLineIndex();
                  break;
               }
            }

            while (var20 < var4) {
               int var24 = var4 - var20;
               if (var17 > var11) {
                  var9 = Math.max(var11, var17 - var24);
               } else {
                  Couple var25 = this.Tq.getPreviousRange(var17);
                  if (var25 == null) {
                     break;
                  }

                  var11 = Longs.maxUnsigned(var5, (Long)var25.getFirst());
                  Longs.minUnsigned(var7, (Long)var25.getSecond());
                  var17 = (Long)var25.getSecond();
                  var9 = Math.max(var11, var17 - var24);
               }

               bat var26 = new bat(0L, this);
               if (!this.q(var26, var9, -1L, var4, -1, var17, var5, var7) || var26.getLineCount() == 0) {
                  break;
               }

               var20 += var26.getLineCount();
               var17 = this.RF(var26.getAnchor(0).getIdentifier());
               var19.prependCodePart(var26);
            }

            return var19;
         }
      } else {
         return null;
      }
   }

   boolean q(bat var1, long var2, long var4, int var6, int var7, long var8, long var10, long var12) {
      int var14 = 0;
      boolean var15 = true;
      Couple var16 = this.Tq.getLocalRange(var2);
      Assert.a(var16 != null, "Cannot find range");
      long var17 = (Long)var16.getFirst();
      long var19 = (Long)var16.getSecond();
      long var21 = Longs.maxUnsigned(var17, var10);
      long var23 = Longs.minUnsigned(var19, var12);
      long var25 = -1L;

      while (true) {
         if (var2 >= var19) {
            var16 = this.Tq.getNextRange(var2);
            if (var16 == null) {
               break;
            }

            var17 = (Long)var16.getFirst();
            var19 = (Long)var16.getSecond();
            var2 = var17;
            var21 = Longs.maxUnsigned(var17, var10);
            var23 = Longs.minUnsigned(var19, var12);
         }

         axg var27 = this.q(var2, var21, var23);
         if (var27 == null) {
            return false;
         }

         int var28 = var1.getLineCount();
         if (var14 == 0 && (var4 == -1L || Longs.compareUnsigned(var2, var4) >= 0)) {
            var14 = var28;
            if (var1.getLineCount() > 1 && var4 >= 0L) {
               long var29 = this.q(var4);
               if (var29 >= 0L) {
                  for (CodeAnchor var32 : var1.getAnchors()) {
                     if (Longs.compareUnsigned(var32.getIdentifier(), var29) > 0) {
                        var14 = var32.getLineIndex();
                        break;
                     }
                  }
               }
            }
         }

         if ((var4 == -1L || Longs.compareUnsigned(var2, var4) >= 0) && var7 >= 0 && var28 - var14 >= var7
            || var8 != -1L && Longs.compareUnsigned(var2, var8) >= 0) {
            break;
         }

         var2 = var27.getMemoryAddress();
         long var36 = this.q(var1, var27, -1, var25, var4, var6, var7);
         if (var36 > 0L) {
            var2 = var36;
         } else {
            var2 += var27.getMemorySize();
         }

         var25 = var2;
         var21 = var2;
         if (var28 == var1.getLineCount()) {
            var1.eol();
            String var37 = Strings.f("No output was generated for address %xh", var27.getMemoryAddress());
            JebCoreService.notifySilentExceptionToClient(new RuntimeException(var37));
         }
      }

      if (var2 == var12) {
         var1.registerAnchor(this.q(var2, true), "a_" + var2);
      }

      return var15;
   }

   private axg q(long var1, long var3, long var5) {
      axg var7 = (axg)this.Gu.getItemOver(var1);
      if (var7 == null) {
         long var8 = var1 & -256L;
         long var10 = var8 + 256L;
         if (Longs.compareUnsigned(var3, var8) > 0) {
            var8 = var3;
         }

         if (Longs.compareUnsigned(var5, var10) < 0) {
            var10 = var5;
         }

         var7 = (axg)this.Gu.getItemOverOrGap(var1, var8, var10);
         if (var7 instanceof axs) {
            var8 = var7.xK();
            var10 = var7.Dw();
            bay.CU var12 = (bay.CU)this.tW.getSegmentContaining(var1);
            if (var12 != null) {
               var8 = Math.max(var8, var12.q);
               var10 = Math.min(var10, var12.RF);
            } else {
               bay.CU var13 = (bay.CU)this.tW.getSegmentBefore(var1);
               if (var13 != null) {
                  var8 = Math.max(var8, var13.RF);
               }

               var13 = (bay.CU)this.tW.getSegmentAfter(var1);
               if (var13 != null) {
                  var10 = Math.min(var10, var13.q);
               }
            }

            var12 = (bay.CU)this.ZA.getSegmentContaining(var1);
            if (var12 != null) {
               var8 = Math.max(var8, var12.q);
               var10 = Math.min(var10, var12.RF);
            } else {
               bay.CU var18 = (bay.CU)this.ZA.getSegmentBefore(var1);
               if (var18 != null) {
                  var8 = Math.max(var8, var18.RF);
               }

               var18 = (bay.CU)this.ZA.getSegmentAfter(var1);
               if (var18 != null) {
                  var10 = Math.min(var10, var18.q);
               }
            }

            if (var7.xK() == var8 && var7.Dw() == var10) {
               return var7;
            }

            var7 = (axg)this.Gu.getGapFactory().create(var8, var10);
         }
      }

      return var7;
   }

   public String q(INativeContinuousItem var1) {
      bat var2 = new bat();
      this.q(var2, var1, -1, -1L, -1L, -1, -1);
      return var2.format();
   }

   long q(bat var1, INativeContinuousItem var2, int var3, long var4, long var6, int var8, int var9) {
      long var10 = var2.getMemoryAddress();
      int var12 = this.q(var2, var6, var8);
      long var13 = -1L;
      if (var12 <= 0) {
         long var15 = this.q(var10);
         var1.registerAnchor(var15, "a_" + var10);
         if (var10 == this.CY.getVirtualImageBase()) {
            this.q(var1);
         }

         var13 = this.q(var1, var10);
         if (var13 == -1L && var4 != -1L && var4 != var2.getMemoryAddress()) {
            this.q(var1, "...", false, true);
            this.q(var1, "... " + S.L("(not allocated)"), false, true);
            this.q(var1, "...", false, true);
         }
      }

      if (var2 instanceof axn) {
         return this.q(var1, (axn)var2);
      } else if (var2 instanceof axs) {
         INativeContinuousItem var18 = this.xK(var10, false);
         if (var18 != null) {
            long var16 = this.q(var1, var18, var3, var4, var13, var8, var9);
            return Math.max(var16, var10 + 1L);
         } else {
            return this.q(var1, (axs)var2, var3, var13);
         }
      } else if (var2 instanceof INativeDataItem) {
         return this.q(var1, (INativeDataItem)var2, var3, var6, var12, var9);
      } else {
         throw new RuntimeException("Unsupported continuous item type: " + var2.getClass().getName());
      }
   }

   ICodeCoordinates q(bat var1, axn var2, long var3) {
      boolean var6 = this.Gu.isRoutineHeader(var3);
      axp var5;
      if (!var6) {
         var5 = this.Gu.xK(var3, false);
      } else {
         var5 = this.CY.RF(var3);
         axo var7 = var5 == null ? null : var5.oW();
         if (var7 == null) {
            Assert.debugFail(Strings.ff("Expecting internal routine at address %Xh", var3));
         } else {
            this.q(var1, var3, var5, var7);
            MethodCoordinates var8 = new MethodCoordinates(var5.getIndex());
            this.q(var1, var8);
            this.q(var1, var3, null, 0, 0, var7, 1, false);
            this.RF(var1, this.Yp.getProcedureDefinitionStart());
            this.RF(var1, var8);
            var1.eol();
            this.RF(var1, var3);
         }
      }

      Object var14;
      if (var5 != null) {
         var14 = new InstructionCoordinates(var5.getIndex(), (int)(var3 - var5.getMemoryAddress()));
      } else {
         var14 = new NativeCoordinates(var3);
      }

      String var15 = this.RF(var3, false);
      boolean var9 = false;
      boolean var10 = this.Gu.isBasicBlockHeader(var3);
      if (var10 && var15 == null) {
         BasicBlock var11 = this.Gu.getBasicBlockHeader(var3);
         if (var11 != null) {
            for (BasicBlock var13 : var11.getInputBlocks()) {
               if (var13.getEndAddress() != var3) {
                  var9 = true;
                  break;
               }
            }
         }
      }

      boolean var16 = false;
      if (!var6 && (var15 != null || var9)) {
         this.q(var1, var3, null, 0, 0, null, 1, true);
         var16 = true;
      }

      if (var14 instanceof InstructionCoordinates) {
         var16 |= this.q(var1, (ICodeCoordinates)var14);
      }

      if (var10 && this.fw && (var5 == null || var5.getMemoryAddress() != var3) && !var16) {
         this.RF(var1, var3);
      }

      return (ICodeCoordinates)var14;
   }

   private void q(bat var1, long var2, axp var4, axo var5) {
      var1.eol();
      var1.eol();
      if (this.jb) {
         if (this.GM > 0) {
            this.q(var1, Strings.generate(this.pQ, ((this.TQ ? this.Qu.length() - 2 : 0) + this.GM) / this.pQ.length()), this.Yw, true);
         }

         for (axp var7 : var5.getMethodReferences()) {
            bbi var8 = var7.nf();
            StringBuilder var9;
            if (var8 == null) {
               var9 = new StringBuilder(S.L("ROUTINE") + ": ").append(var7.getName(true));
            } else {
               var9 = new StringBuilder(S.L("METHOD") + ": ").append(var8.getName(true)).append("::").append(var7.getName(true));
            }

            this.q(var1, var9, this.Yw, true);
         }

         Set var22 = var5.getBadAddresses();
         if (var22 != null && !var22.isEmpty()) {
            int var23 = var22.size();
            StringBuilder var25 = new StringBuilder();
            Strings.ff(var25, S.L("(Parsing failed at %d addresses:"), var23);
            var25.append(" ");
            int var27 = 0;

            for (Long var11 : var22) {
               if (var27 >= 1) {
                  var25.append(", ");
               }

               var25.append(Formatter.toHexString(var11, true)).append("h");
               var27++;
            }

            var25.append(")");
            this.q(var1, var25.toString(), this.Yw, true);
         }

         CFG var24 = var5.getCFG();
         long var26 = var24.getFirstAddress();
         if (Longs.compareUnsigned(var2, var26) > 0) {
            this.q(var1, Strings.ff(S.L("(Routine entry is located after routine lowest address %Xh)"), var26), this.Yw, true);
         }

         List var28 = var24.getExitBlocks();
         int var29 = var28.size();
         if (var29 != 1) {
            if (var29 == 0) {
               this.q(var1, S.L("(Routine has no exit node)"), this.Yw, true);
            } else {
               StringBuilder var12 = new StringBuilder("(");
               Strings.ff(var12, S.L("Routine has %d exit nodes"), var29);
               var12.append(": ");
               int var13 = 0;

               for (BasicBlock var15 : var28) {
                  if (var13 >= 1) {
                     var12.append(", ");
                  }

                  Strings.ff(var12, "%Xh", var15.getFirstAddress());
                  var13++;
               }

               var12.append(")");
               this.q(var1, var12.toString(), this.Yw, true);
            }
         }

         List var30 = var24.getGaps();
         if (!var30.isEmpty()) {
            StringBuilder var31 = new StringBuilder("(");
            var31.append(S.L("Routine has gaps"));
            var31.append(": ");
            int var33 = 0;

            for (Couple var16 : var30) {
               if (var33 >= 1) {
                  var31.append(", ");
               }

               var31.append(Formatter.toHexString((Long)var16.getFirst(), true))
                  .append("h-")
                  .append(Formatter.toHexString((Long)var16.getSecond(), true))
                  .append("h");
               var33++;
            }

            var31.append(")");
            this.q(var1, var31.toString(), this.Yw, true);
         }

         Boolean var32 = var4.getNonReturning();
         if (var32 != null && var32) {
            this.q(var1, S.L("(Routine does not return)"), this.Yw, true);
         }

         if (var4.If()) {
            this.q(var1, S.L("(Routine's name comes from a caller identified as library code)"), this.Yw, true);
         }

         if (var5.oW() != null) {
            if (var5.oW().oW() != null) {
               String var34 = Strings.ff(S.L("(Trampoline to internal routine at %Xh: %s)"), var5.oW().getMemoryAddress(), var5.oW().getAddress());
               this.q(var1, var34, this.Yw, true);
            } else {
               String var35 = Strings.ff(S.L("(Trampoline to external routine %s)"), var5.oW().getAddress());
               this.q(var1, var35, this.Yw, true);
            }
         }

         if (var4.Uv() != null) {
            String var36 = var4.getSignature(true);
            this.q(var1, "", this.Yw, true);
            this.q(var1, S.L("Signature") + ": " + var36, this.Yw, true);
         }

         String var37 = var4.Xo();
         if (var37 != null) {
            this.q(var1, "", this.Yw, true);
            this.q(var1, S.L("DWARF Signature") + ": " + var37, this.Yw, true);
         }

         String var39 = var4.jq();
         if (var39 != null) {
            this.q(var1, "", this.Yw, true);
            this.q(var1, S.L("Demangled declaration") + ": " + var39, this.Yw, true);
         }

         String var40 = var4.ui();
         if (var40 != null && var39 == null) {
            this.q(var1, "", this.Yw, true);
            this.q(var1, S.L("Demangled name") + ": " + var40, this.Yw, true);
         }

         if (var40 != null || var39 != null) {
            String var17 = var4.mI();
            if (var17 != null) {
               this.q(var1, "| " + S.L("original name") + ": " + var17, this.Yw, true);
            }
         }

         com.pnfsoftware.jeb.corei.parsers.asm.nativesig.oL var41 = var4.wF();
         if (var41 != null) {
            INativeSignature var18 = var41.q();
            if (var18 != null) {
               this.q(var1, "", this.Yw, true);
               this.q(var1, S.L("Best matching routine signature:"), this.Yw, true);
               this.q(var1, var18);
            }

            List var19 = var41.RF();
            if (var19 != null) {
               this.q(var1, "", this.Yw, true);
               this.q(var1, S.L("Alternate matching routine signatures:"), this.Yw, true);

               for (INativeSignature var21 : var19) {
                  this.q(var1, var21);
               }
            }
         }

         com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.CU var42 = var4.Dz();
         if (var42 != null) {
            if (var42.RF() != null) {
               this.q(var1, "", this.Yw, true);
               this.q(var1, S.L("Codeless signature match") + ": " + var42.RF().toString(), this.Yw, true);
            } else if (var42.xK() != null) {
               this.q(var1, "", this.Yw, true);
               this.q(var1, Strings.ff(S.L("Codeless signature possible matches (%d): %s"), var42.xK().size(), var42.xK().toString()), this.Yw, true);
            }
         }

         String var43 = this.Yp.generateExtraMethodComment(var2);
         if (var43 != null) {
            this.q(var1, var43, this.Yw, true);
         }

         var1.eol();
      }
   }

   private void q(bat var1, INativeSignature var2) {
      boolean var3 = false;
      if (var2.getFlags().hasMeaningfulTargetName()) {
         this.q(var1, "| " + S.L("name:") + var2.getTargetName(), this.Yw, true);
         if (var2.getAlternateNames() != null && var2.getAlternateNames().size() != 0) {
            this.q(var1, "| " + S.L("alternate names:") + Strings.join(",", var2.getAlternateNames()), this.Yw, true);
         }

         var3 = true;
      } else if (var2.getPossibleNames() != null && var2.getPossibleNames().size() != 0) {
         this.q(var1, "| " + S.L("possible names:") + Strings.join(",", var2.getPossibleNames()), this.Yw, true);
         var3 = true;
      }

      if (var3) {
         Set var4 = ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var2).xK();
         if (var4 != null && var4.size() > 0) {
            StringBuilder var5 = new StringBuilder();
            var5.append("| " + S.L("packages:"));
            boolean var6 = true;

            for (String var8 : var4) {
               if (var8 != null && !var8.isEmpty()) {
                  if (!var6) {
                     var5.append(", ");
                  }

                  var5.append(var8);
                  if (var6) {
                     var6 = false;
                  }
               }
            }

            this.q(var1, var5.toString(), this.Yw, true);
         }

         if (var2.getAttributes() != null) {
            for (INativeAttribute var10 : var2.getAttributes()) {
               if (!(var10 instanceof azt) && var10.isPrintable()) {
                  this.q(var1, "| " + var10.toString(), this.Yw, true);
               }
            }
         }
      }
   }

   private axo xK(long var1) {
      List var3 = this.Gu.getContainedRoutineAddresses(var1);
      if (var3.isEmpty()) {
         return null;
      } else {
         axo var4 = null;

         for (long var6 : var3) {
            axp var8 = this.CY.RF(var6);
            axo var9 = var8 == null ? null : var8.oW();
            if (var9 == null) {
               Assert.debugFail(Strings.ff("Expecting internal routine at address %Xh", var1));
            } else {
               if (var1 == var9.getCFG().getLastAddress()) {
                  return var9;
               }

               if (this.GM > 0 && var4 == null) {
                  var4 = var9;
               }
            }
         }

         return var4;
      }
   }

   void RF(bat var1, axn var2, long var3) {
      axo var5 = this.xK(var3);
      if (var5 != null) {
         if (var3 == var5.getCFG().getLastAddress()) {
            this.RF(var1, var3);
            this.q(var1, var3, null, 0, 0, var5, 1, false);
            this.RF(var1, this.Yp.getProcedureDefinitionEnd());
            var1.eol();
            if (this.GM > 0 && this.jb) {
               this.q(var1, Strings.generate(this.kf, ((this.TQ ? this.Qu.length() - 2 : 0) + this.GM) / this.kf.length()), this.Yw, true);
               var1.eol();
            }
         } else if (this.GM > 0 && this.jb) {
            long var6 = var2.getMemoryAddress() + var2.getMemorySize();
            if (!this.Gu.getContainedRoutineAddresses(var6).contains(var5.getMemoryAddress())) {
               this.q(var1, Strings.generate(this.kf, ((this.TQ ? this.Qu.length() - 2 : 0) + this.GM) / this.kf.length()), this.Yw, true);
            }
         }
      }
   }

   long q(bat var1, axn var2) {
      long var3 = var2.getMemoryAddress();
      ICodeCoordinates var5 = this.q(var1, var2, var3);
      IInstruction var6 = var2.getInstruction();
      byte[] var7 = var6.getCode();
      this.q(var1, var3, var7, 0, var7.length, var2, -1, false);
      this.Yp.formatInstruction(var3, var6, var1);
      String var8 = this.q(var2);
      this.q(var1, var3, var5, var8, true);
      this.RF(var1, var2, var3);
      return var2.getMemoryAddressEnd();
   }

   void q(bat var1, INativeDataItem var2) {
      if (var2 instanceof axr) {
         INativeType var3 = var2.getType();
         this.q(var1, var2.getMemoryAddress(), null, 0, 0, var2, -1, false);
         this.q(var1, this.Qu);
         this.q(var1, var3.getName(true), false, true);
      }
   }

   void RF(bat var1, INativeDataItem var2) {
   }

   private int q(INativeContinuousItem var1, long var2, int var4) {
      int var5 = 0;
      if (var4 > 0 && var1 instanceof axq && !(var1 instanceof axw)) {
         axq var6 = (axq)var1;
         if (var6.CE() > 1000) {
            INativeType var7 = TypeUtil.getNonAlias(var6.wF());
            double var8 = var7 instanceof bbv ? 1.0 / ((bbv)var7).getFieldsCount() : this.Yp.getArrayElementPerLine();
            long var10 = var1.getMemoryAddress();
            if (var2 == -1L) {
               var5 = var6.CE() - (int)(var4 * var8) * 3;
               return var5 >= 0 ? var5 : 0;
            }

            long var12 = var2 - var10;
            if (var12 > 0L && var12 < 2147483647L) {
               int var14 = var7.getSize();
               int var15 = (int)(var12 / var14);
               if (var15 < var6.CE()) {
                  var5 = var15 - (int)(var4 * var8) * 3;
               }
            }
         }
      }

      return var5 >= 0 ? var5 : 0;
   }

   long q(bat var1, INativeDataItem var2, int var3, long var4, int var6, int var7) {
      Assert.a(!(var2 instanceof axs));
      long var8 = var2.getMemoryAddress();
      int var10 = (int)var2.getMemorySize();
      int var11 = 0;
      if (this.Wp && !(var2 instanceof axr)) {
         var11 = this.q(var2);
      }

      byte[] var12 = new byte[var10 + var11];
      int var13 = VirtualMemoryUtil.readBytesSafe(this.CY.getMemory(), var8, var12.length, var12, 0, 1);
      Assert.a(var13 == var10 + var11);
      this.q(var1, var2);
      int var14 = 0;
      bax var15 = this.q(var12, 0, var2, var1, new ArrayDeque());
      bax var16 = null;
      if (!(var2 instanceof axv)) {
         bat var17 = new bat();
         var16 = this.q(var12, 0, var2, var17, new ArrayDeque());
         if (var6 > 0 && var15 instanceof bar) {
            int var18 = ((axq)var2).wF().getSize();
            ((bar)var15).lm = var6;
            ((bar)var16).lm = var6;
            ((bar)var15).xK = var6 * var18;
            ((bar)var16).xK = var6 * var18;
         }
      }

      int var28 = 0;

      while (var15.q()) {
         Assert.a(var16 == null || var16.q());
         int var29 = var15.Dw();
         long var19 = var8 + var29;
         if (var4 != -1L && Longs.compareUnsigned(var19, var4) >= 0) {
            var28++;
         }

         NativeCoordinates var21 = new NativeCoordinates(var19);
         if (Longs.compareUnsigned(var19, var8) > 0) {
            long var22 = this.q(var19, true);
            var1.registerAnchor(var22, "a_" + var19);
         }

         int var30 = var16 == null ? var15.xK() : var16.Uv();
         boolean var23 = var15.RF();
         this.q(var1, var19, var12, var29, Math.min(var30, var10 - var29) + (var23 ? var11 : 0), var2, var14 == 0 ? 0 : -1, false);
         var15.Uv();
         if (var23 && var11 > 0) {
            CharSequence var24 = var1.getCurrentLine().getText();
            if (var24.length() > 4 && var24.charAt(var24.length() - 1) == '0' && var24.charAt(var24.length() - 2) == ',') {
               var1.append("{" + (1 + var11) + "}");
            } else if (var11 == 1) {
               var1.append(",0");
            } else {
               var1.append(",0{" + var11 + "}");
            }
         }

         String var31 = "";
         if (var1.RF()) {
            List var25 = var1.Dw();
            String var26 = (String)var25.get(0);
            if (var25.size() >= 2) {
               String var27 = (String)var25.get(var25.size() - 1);
               if (!var26.equals(var27)) {
                  var26 = var26 + " ... " + var27;
               }
            }

            var31 = var31 + var26;
         }

         String var32 = this.q(var2, var19);
         if (var32 != null && var32.length() > 0) {
            if (var31.length() > 0) {
               var31 = var31 + "\n";
            }

            var31 = var31 + var32;
         }

         this.q(var1, var19, var21, var31, true);
         var14++;
         if (var3 != -1 && var14 > var3 || var7 > 0 && var28 > 0 && var28 >= var7 * 3) {
            break;
         }
      }

      return var2.getMemoryAddressEnd() + var11;
   }

   private INativeContinuousItem xK(long var1, boolean var3) {
      if (this.Wp) {
         if (var3) {
            INativeContinuousItem var4 = this.Gu.getItemOver(var1);
            if (var4 != null && !(var4 instanceof axs)) {
               return null;
            }
         }

         INativeContinuousItem var5 = this.Gu.getPreviousItem(var1);
         if (var5 instanceof INativeDataItem && !(var5 instanceof axr) && this.q((INativeDataItem)var5) > 0) {
            return var5;
         }
      }

      return null;
   }

   private int q(INativeDataItem var1) {
      this.Gu.getItemAt(var1.getMemoryAddressEnd());
      if (!(var1 instanceof axw) && var1.getMemorySize() != 1L && var1.getMemorySize() != 2L) {
         return 0;
      } else {
         INativeContinuousItem var2 = this.Gu.getNextItem(var1);
         if (var2 != null && var2.getMemoryAddress() - var1.getMemoryAddressEnd() < 32L && this.RF(var1.getMemoryAddressEnd(), var2.getMemoryAddress())) {
            NativeCoordinates var3 = new NativeCoordinates(var1.getMemoryAddressEnd());
            if (this.CY.getCommentManager().getComment2(var3) == null && this.Gu.HF().getReferencesTo(var1.getMemoryAddressEnd()).isEmpty()) {
               int var4 = (int)(var2.getMemoryAddress() - var1.getMemoryAddressEnd());
               if (this.LL) {
                  bay.CU var5 = (bay.CU)this.tW.getSegmentAfter(var1.getMemoryAddressEnd() - 1L);
                  if (var5 != null && var5.q() < var1.getMemoryAddressEnd() + var4) {
                     var4 = (int)(var5.q() - var1.getMemoryAddressEnd());
                  }

                  var5 = (bay.CU)this.ZA.getSegmentAfter(var1.getMemoryAddressEnd() - 1L);
                  if (var5 != null && var5.q() < var1.getMemoryAddressEnd() + var4) {
                     var4 = (int)(var5.q() - var1.getMemoryAddressEnd());
                  }
               }

               return var4;
            } else {
               return 0;
            }
         } else {
            return 0;
         }
      }
   }

   private boolean RF(long var1, long var3) {
      for (long var5 = var1; var5 < var3; var5++) {
         try {
            if (this.WI.readByte(var5) != 0) {
               return false;
            }
         } catch (MemoryException var7) {
            return false;
         }
      }

      return true;
   }

   void q(bat var1, axg var2) {
   }

   void RF(bat var1, axg var2) {
   }

   long q(bat var1, axs var2, int var3, long var4) {
      long var6 = var2.getMemoryAddress();
      long var8 = var2.getMemorySize();
      int var10 = (int)Math.min(var8, 1048576L);
      byte[] var11 = new byte[var10];
      int var12 = VirtualMemoryUtil.readBytesSafe(this.CY.getMemory(), var6, var10, var11, 0, 1);
      int var13 = 16;
      if (this.PY > 0 && this.PY < 4096 && MathUtil.isPowerOfTwo(this.PY)) {
         var13 = this.PY;
      }

      int var14 = 1;
      if (this.cR == 1 || this.cR == 2 || this.cR == 4 || this.cR == 8) {
         var14 = this.cR;
      }

      String var15 = this.Yp.getRawDataDeclarator(8);
      String var16 = this.Yp.getDataSeparator();
      int var17 = 0;
      int var18 = Math.min(var12, var13 - (int)(var6 % var13));

      while (var17 < var12) {
         long var19 = var6 + var17;
         NativeCoordinates var21 = new NativeCoordinates(var19);
         if (Longs.compareUnsigned(var19, var6) > 0) {
            long var22 = this.q(var19, true);
            var1.registerAnchor(var22, "a_" + var19);
         }

         this.q(var1, var19, var11, var17, var18, null, -1, false);
         if (!this.cY) {
            var1.appendAndRecord(var15, ItemClassIdentifiers.KEYWORD);
            StringBuilder var28 = new StringBuilder();

            for (int var23 = 0; var23 < var18; var23++) {
               if (var23 >= 1) {
                  var28.append(var16);
               }

               byte var24 = var11[var17 + var23];
               if (var24 >= 0 && var24 <= 9) {
                  var28.append(" ").append((int)var24);
               } else if (!Characters.isAsciiChar(var24)) {
                  var28.append(" ").append(Formatter.toHexString(var24, true, 2)).append("h");
               } else {
                  int var25 = var23 + 1;

                  while (var25 < var18 && Characters.isAsciiChar(var11[var17 + var25])) {
                     var25++;
                  }

                  int var26 = var25 - var23;
                  if (var26 == 1) {
                     var28.append(" '").append(Character.toString((char)var24)).append("'");
                  } else {
                     String var27 = Strings.decodeASCII(var11, var17 + var23, var26);
                     var28.append(" \"").append(var27).append("\"");
                     var23 += var26 - 1;
                  }
               }
            }

            var1.append(var28.toString());
         } else {
            var1.appendAndRecord("dr", ItemClassIdentifiers.KEYWORD);
            StringBuilder var29 = new StringBuilder();
            ByteBuffer var30 = ByteBuffer.wrap(var11, var17, var18);
            if (this.CY.getEndianness() != null) {
               var30.order(this.CY.getEndianness().toByteOrder());
            }

            while (var30.hasRemaining()) {
               if (var14 >= 8 && var30.remaining() >= 8) {
                  var29.append(" ").append(Formatter.toHexString(var30.getLong(), true, 16));
               } else if (var14 >= 4 && var30.remaining() >= 4) {
                  var29.append(" ").append(Formatter.toHexString(var30.getInt(), true, 8));
               } else if (var14 >= 2 && var30.remaining() >= 2) {
                  var29.append(" ").append(Formatter.toHexString(var30.getShort(), true, 4));
               } else {
                  var29.append(" ").append(Formatter.toHexString(var30.get(), true, 2));
               }
            }

            var1.append(var29.toString());
         }

         this.q(var1, var19, var21, null, true);
         var17 += var18;
         var18 = Math.min(var12 - var17, var13);
         if (var3 >= 0 && var1.getLineCount() >= var3) {
            break;
         }
      }

      return var2.getMemoryAddressEnd();
   }

   void q(bat var1, long var2, boolean var4) {
      StringBuilder var5 = new StringBuilder();
      bay.CU var6 = (bay.CU)this.tW.getSegmentContaining(var2);
      bay.CU var7 = (bay.CU)this.ZA.getSegmentContaining(var2);
      String var8;
      if (var6 == null && var7 == null) {
         var8 = "SLACK";
      } else if (var6 != null && var7 == null) {
         var8 = var6.Dw;
      } else if (var6 != null && var6.Dw.length() + var7.Dw.length() <= this.br && !var6.Dw.equals(var7.Dw)) {
         var8 = var6.Dw + var7.Dw;
      } else {
         var8 = var7.Dw;
      }

      var5.append(var8);
      var5.append(':');
      long var9 = this.CY.getPhysicalImageDelta() + var2;
      int var11 = this.CY.getMemory().getSpaceBits();
      if (var11 <= 32) {
         var5.append(Formatter.toHexString(var9, true, 8));
      } else if (var11 <= 64) {
         if (this.eC != null && !this.eC.isEmpty()) {
            var5.append(Formatter.toHexString((int)(var9 >> 32), true, 8)).append(this.eC).append(Formatter.toHexString((int)var9, true, 8));
         } else {
            var5.append(Formatter.toHexString(var9, true, 16));
         }
      } else {
         Strings.ff(var5, "%16X", var9);
      }

      ItemClassIdentifiers var12 = var6 != null ? ItemClassIdentifiers.ADDRESS : ItemClassIdentifiers.ADDRESS_SLACK;
      var1.appendAndRecord(var5.toString(), var12);
      if (var4) {
         int var13 = this.br - var8.length();
         var1.space(var13);
         var1.space(1);
      }
   }

   void q(bat var1, byte[] var2, int var3, int var4) {
      StringBuilder var5 = new StringBuilder();
      if (var2 == null) {
         var5.append(Strings.pad(' ', this.nv * 3));
      } else {
         var5.append(Formatter.formatBinaryLineTruncate(var2, var3, var4, this.nv));
      }

      var5.append(" ");
      var1.appendAndRecord(var5.toString(), ItemClassIdentifiers.BYTECODE);
   }

   void q(bat var1, byte[] var2) {
      this.q(var1, var2, 0, var2.length);
   }

   void q(bat var1, long var2, INativeMemoryItem var4, int var5, boolean var6) {
      if (var5 < 0) {
         var1.space(this.IY + 1);
      } else {
         String var7 = null;
         if (var4 != null) {
            var7 = var4.getName(true);
         }

         if (var7 == null) {
            var7 = this.RF(var2, var5 > 0);
         }

         if (var7 == null) {
            var1.space(this.IY + 1);
         } else {
            this.q(var1, var2, var7, true, var6);
            Collection var8 = this.CY.getCodeModel().getLabelManager().getAlternateLabels(var2);
            if (var8 != null) {
               for (String var10 : var8) {
                  var1.eol();
                  this.q(var1, this.ND);
                  this.q(var1, var2, var10, false, var6);
               }
            }
         }
      }

      if (var6) {
         var1.eol();
      }
   }

   private void q(bat var1, long var2, String var4, boolean var5, boolean var6) {
      String var7 = var4;
      if (var6) {
         var7 = this.Yp.getLabelPrefix() + var4 + this.Yp.getLabelSuffix();
      }

      ItemClassIdentifiers var8;
      if (var5) {
         var8 = this.Yp.getBestClassIdForAddress(var2);
      } else {
         var8 = ItemClassIdentifiers.LABEL_ALTERNATE;
      }

      var1.appendAndRecord(var7, var8, this.CY.gO(var2));
      if (!var6) {
         if (var7.length() < this.IY) {
            int var9 = this.IY - var7.length();
            var1.space(var9);
         }

         var1.space();
      }
   }

   void q(bat var1, CharSequence var2, boolean var3, boolean var4) {
      this.q(var1, var2, var3 ? 2 : 0, var4);
   }

   void q(bat var1, CharSequence var2, int var3, boolean var4) {
      if (var3 != 0) {
         this.q(var1, switch (var3) {
            case 1 -> this.ND;
            case 2 -> this.Qu;
            case 3 -> this.jh;
            default -> "";
         });
      }

      String var6 = this.Yp.getInlineCommentString() + " " + var2;
      var1.appendComment(var6, false);
      if (var4) {
         var1.eol();
      }
   }

   void q(bat var1, String var2, String var3) {
      if (var3 != null && this.q(var1, var3) <= 0) {
         var1.space();
      }

      int var4 = 0;
      String[] var5 = Strings.splitLines(var2);

      for (String var9 : var5) {
         if (var4 >= 1) {
            var1.eol();
            if (var3 != null) {
               this.q(var1, var3);
            }
         }

         String var10 = this.Yp.getInlineCommentString() + " " + var9;
         var1.appendComment(var10);
         var4++;
      }
   }

   int q(bat var1, String var2) {
      int var3 = var2.length() - var1.getCurrentLineLength();
      if (var3 > 0) {
         var1.space(var3);
      }

      return var3;
   }

   public static void q(IPropertyDefinitionManager var0) {
      IPropertyDefinitionGroup var1 = var0.addGroup("text");
      var1.addDefinition("ShowAddresses", PropertyTypeBoolean.create(true), S.L("Display addresses of items"), 0);
      var1.addDefinition(
         "ShowBytesCount", PropertyTypeInteger.createPositiveOrZero(0), S.L("Maximum count of instruction bytes to be displayed before the instruction"), 0
      );
      var1.addDefinition("ShowSegmentHeaders", PropertyTypeBoolean.create(true), S.L("Display the segment or section headers"));
      var1.addDefinition("SegmentHeadersSeparator", PropertyTypeString.create("."), S.L("Segment Header character separator"), 16);
      var1.addDefinition(
         "SegmentHeadersSeparatorLength", PropertyTypeInteger.createPositiveOrZero(80), S.L("Number of characters used to build the line in Segment Header")
      );
      var1.addDefinition(
         "SegmentHeadersSeparatorLengthABL",
         PropertyTypeBoolean.create(true),
         S.L("Use prefix (address / byte / label) length for the Segment Header separator")
      );
      var1.addDefinition("SegmentHeadersIndentABL", PropertyTypeBoolean.create(false), S.L("Indent Segment Header using (address / byte / label) length"));
      var1.addDefinition("ShowRoutineHeaders", PropertyTypeBoolean.create(true), S.L("Display the routine headers and footers"));
      var1.addDefinition("RoutineHeadersSeparator", PropertyTypeString.create("="), S.L("Routine Header character separator"), 16);
      var1.addDefinition("RoutineEndSeparator", PropertyTypeString.create("-"), S.L("Routine End character separator"), 16);
      var1.addDefinition(
         "RoutineSeparatorLength", PropertyTypeInteger.createPositiveOrZero(80), S.L("Number of characters used to build the line separating routines")
      );
      var1.addDefinition(
         "RoutineHeadersSeparatorLengthABL",
         PropertyTypeBoolean.create(false),
         S.L("Use prefix (address / byte / label) length for the Routine Header separator")
      );
      var1.addDefinition("RoutineHeadersIndentABL", PropertyTypeBoolean.create(true), S.L("Indent Routine Header using (address / byte / label) length"));
      var1.addDefinition("ShowSpaceBetweenBlocks", PropertyTypeBoolean.create(false), S.L("Insert blank lines between basic blocks"));
      var1.addDefinition("Hide0Padding", PropertyTypeBoolean.create(true), S.L("Hide 0 Padding between declared data"));
      var1.addDefinition(
         "LabelAreaLength", PropertyTypeInteger.createPositiveOrZero(16), S.L("Length in characters of the assembly column containing the labels")
      );
      var1.addDefinition(
         "InstructionAreaLength", PropertyTypeInteger.createPositiveOrZero(40), S.L("Length in characters of the assembly column containing the instructions")
      );
      var1.addDefinition("BlockXrefsCount", PropertyTypeInteger.create(50), S.L("Maximum number of cross-references displayed at any address"));
      var1.addDefinition(
         "GapPreferRawFormatting",
         PropertyTypeBoolean.create(false),
         S.L("Prefer raw formatting (dr XX, dr XXXX) for gaps and slack spaces instead of regular declarations (e.g., db ??)")
      );
      var1.addDefinition(
         "GapRawBytesPerLine",
         PropertyTypeInteger.createPositiveOrZero(16),
         S.L("For raw formatting, determine the maximum number of bytes per line in gaps and slack spaces. Must be a power of 2, else reverts to the default.")
      );
      var1.addDefinition(
         "GapRawIntegerSize",
         PropertyTypeInteger.createPositiveOrZero(1),
         S.L("For raw formatting, determine the integer length (in bytes) used for rendering. Must be either 1, 2, 4, or 8, else reverts to the default")
      );
      var1.addDefinition(
         "CharBreak64BitAddresses",
         PropertyTypeString.create("'"),
         S.L("If not empty, the address column will display 64-bit addresses as two 8-hexdigit parts, separated by the provided character string"),
         16
      );
   }

   private void q(boolean var1) {
      IPropertyManager var2 = this.CY.getPropertyManager();
      if (this.Ov != null && this.Ov.getShowBytesCount() != null) {
         this.nv = this.Ov.getShowBytesCount();
      } else {
         this.nv = var2.getInteger("ShowBytesCount", 0);
      }

      if (this.Ov != null && this.Ov.getShowAddresses() != null) {
         this.Lj = this.Ov.getShowAddresses();
      } else {
         this.Lj = var2.getBoolean("ShowAddresses", true);
      }

      if (this.Ov != null && this.Ov.getShowSegmentHeaders() != null) {
         this.LL = this.Ov.getShowSegmentHeaders();
      } else {
         this.LL = var2.getBoolean("ShowSegmentHeaders", true);
      }

      if (this.Ov != null && this.Ov.getSegmentHeadersSeparator() != null) {
         this.PQ = this.Ov.getSegmentHeadersSeparator();
      } else {
         this.PQ = var2.getString("SegmentHeadersSeparator", ".");
      }

      if (Strings.isBlank(this.PQ)) {
         this.PQ = ".";
      }

      if (this.Ov != null && this.Ov.getSegmentHeadersSeparatorLength() != null) {
         this.fQ = this.Ov.getSegmentHeadersSeparatorLength();
      } else {
         this.fQ = var2.getInteger("SegmentHeadersSeparatorLength", 80);
      }

      if (this.Ov != null && this.Ov.getSegmentHeadersSeparatorLengthABL() != null) {
         this.fi = this.Ov.getSegmentHeadersSeparatorLengthABL();
      } else {
         this.fi = var2.getBoolean("SegmentHeadersSeparatorLengthABL", true);
      }

      if (this.Ov != null && this.Ov.getSegmentHeadersIndentABL() != null) {
         this.bl = this.Ov.getSegmentHeadersIndentABL();
      } else {
         this.bl = var2.getBoolean("SegmentHeadersIndentABL", false);
      }

      if (this.Ov != null && this.Ov.getShowRoutineHeaders() != null) {
         this.jb = this.Ov.getShowRoutineHeaders();
      } else {
         this.jb = var2.getBoolean("ShowRoutineHeaders", true);
      }

      if (this.Ov != null && this.Ov.getRoutineHeadersSeparator() != null) {
         this.pQ = this.Ov.getRoutineHeadersSeparator();
      } else {
         this.pQ = var2.getString("RoutineHeadersSeparator", "=");
      }

      if (Strings.isBlank(this.pQ)) {
         this.pQ = "=";
      }

      if (this.Ov != null && this.Ov.getRoutineEndSeparator() != null) {
         this.kf = this.Ov.getRoutineEndSeparator();
      } else {
         this.kf = var2.getString("RoutineEndSeparator", "-");
      }

      if (Strings.isBlank(this.kf)) {
         this.kf = "-";
      }

      if (this.Ov != null && this.Ov.getRoutineSeparatorLength() != null) {
         this.GM = this.Ov.getRoutineSeparatorLength();
      } else {
         this.GM = var2.getInteger("RoutineSeparatorLength", 80);
      }

      if (this.Ov != null && this.Ov.getRoutineHeadersSeparatorLengthABL() != null) {
         this.TQ = this.Ov.getRoutineHeadersSeparatorLengthABL();
      } else {
         this.TQ = var2.getBoolean("RoutineHeadersSeparatorLengthABL", false);
      }

      if (this.Ov != null && this.Ov.getRoutineHeadersIndentABL() != null) {
         this.Yw = this.Ov.getRoutineHeadersIndentABL();
      } else {
         this.Yw = var2.getBoolean("RoutineHeadersIndentABL", true);
      }

      if (this.Ov != null && this.Ov.getShowSpaceBetweenBlocks() != null) {
         this.fw = this.Ov.getShowSpaceBetweenBlocks();
      } else {
         this.fw = var2.getBoolean("ShowSpaceBetweenBlocks", false);
      }

      if (this.Ov != null && this.Ov.getHide0Padding() != null) {
         this.Wp = this.Ov.getHide0Padding();
      } else {
         this.Wp = var2.getBoolean("Hide0Padding", true);
      }

      if (this.Ov != null && this.Ov.getLabelAreaLength() != null) {
         this.IY = this.Ov.getLabelAreaLength();
      } else {
         this.IY = var2.getInteger("LabelAreaLength", 16);
      }

      if (this.Ov != null && this.Ov.getInstructionAreaLength() != null) {
         this.qR = this.Ov.getInstructionAreaLength();
      } else {
         this.qR = var2.getInteger("InstructionAreaLength", 40);
      }

      if (this.Ov != null && this.Ov.getBlockXrefsCount() != null) {
         this.YA = this.Ov.getBlockXrefsCount();
      } else {
         this.YA = var2.getInteger("BlockXrefsCount", 50);
      }

      if (this.Ov != null && this.Ov.getGapPreferRawFormatting() != null) {
         this.cY = this.Ov.getGapPreferRawFormatting();
      } else {
         this.cY = var2.getBoolean("GapPreferRawFormatting", false);
      }

      if (this.Ov != null && this.Ov.getGapRawBytesPerLine() != null) {
         this.PY = this.Ov.getGapRawBytesPerLine();
      } else {
         this.PY = var2.getInteger("GapRawBytesPerLine", 16);
      }

      if (this.Ov != null && this.Ov.getGapRawIntegerSize() != null) {
         this.cR = this.Ov.getGapRawIntegerSize();
      } else {
         this.cR = var2.getInteger("GapRawIntegerSize", 1);
      }

      if (this.Ov != null && this.Ov.getGapRawIntegerSize() != null) {
         this.eC = this.Ov.getCharBreak64BitAddresses();
      } else {
         this.eC = var2.getString("CharBreak64BitAddresses", "'");
      }

      ICodeObjectUnit var3 = this.CY.getCodeObjectContainer();
      if (var3 != null) {
         this.q(var3.getValidSegments(), true, this.tW);
         this.q(var3.getValidSections(), false, this.ZA);
      }

      this.ND = Strings.generate(' ', this.q(true, true, false, false));
      this.Qu = Strings.generate(' ', this.q(true, true, true, false));
      this.jh = Strings.generate(' ', this.q(true, true, true, true));
      if (var1) {
         this.notifyListeners(new JebEvent(J.UnitChange));
      }
   }

   @Override
   public NativeDisassemblyProperties getPropertyOverrides() {
      return this.Ov;
   }

   @Override
   public void setPropertyOverrides(NativeDisassemblyProperties var1) {
      this.Ov = var1;
      this.q(true);
   }

   private void q(Iterable var1, boolean var2, SegmentMap var3) {
      for (ISegmentInformation var5 : var1) {
         if ((var2 || var5.getOffsetInMemory() != 0L) && var5.getSizeInMemory() != 0L) {
            String var6 = var5.getName();
            if (var6 != null && var6.length() > 9) {
               var6 = var6.substring(0, 9);
            } else {
               var6 = Strings.safe(var6);
            }

            bay.CU var7;
            try {
               var7 = new bay.CU(var5, var6);
            } catch (RuntimeException var8) {
               continue;
            }

            if (var3.isEmptyRange(var7.q(), var7.RF())) {
               var3.add(var7);
               if (var6.length() > this.br) {
                  this.br = var6.length();
               }
            }
         }
      }
   }

   private int q(boolean var1, boolean var2, boolean var3, boolean var4) {
      int var10 = 0;
      if (var1 && this.Lj) {
         var10 += this.br + 1;
         int var6 = this.CY.getMemory().getSpaceBits();
         if (var6 <= 32) {
            var10 += 8;
         } else if (var6 <= 64) {
            var10 += 17;
         } else {
            var10 += 16;
         }

         var10++;
      }

      if (var2 && this.nv > 0) {
         var10 += this.nv * 3;
         var10++;
      }

      if (var3 && this.IY > 0) {
         var10 += this.IY;
         var10++;
      }

      if (var4 && this.qR > 0) {
         var10 += this.qR;
      }

      return var10;
   }

   long q(bat var1, long var2) {
      if (!this.LL) {
         return -1L;
      } else {
         bay.CU var4 = (bay.CU)this.tW.get(var2);
         bay.CU var5 = (bay.CU)this.ZA.get(var2);
         if (var4 == null && var5 == null) {
            return -1L;
         } else {
            var1.eol();
            String var6 = Strings.generate(this.PQ, ((this.fi ? this.Qu.length() - 2 : 0) + this.fQ) / this.PQ.length());
            this.q(var1, var6, this.bl, true);
            this.q(var1, "", this.bl, true);
            long var7 = 1L;
            if (var4 != null) {
               this.q(var1, var4.xK, true, var5 == null ? null : var5.xK);
               var7 = var4.RF;
               String var9 = this.Yp.generateExtraSegmentHeader(var4.xK);
               if (var9 != null) {
                  this.q(var1, var9, null);
               }
            }

            if (var5 != null) {
               this.q(var1, var5.xK, false, var4 == null ? null : var4.xK);
               var7 = var7 == -1L ? var5.RF : Math.min(var7, var5.RF);
               String var10 = this.Yp.generateExtraSectionHeader(var5.xK);
               if (var10 != null) {
                  this.q(var1, var10, null);
               }
            }

            this.q(var1, "", this.bl, true);
            this.q(var1, var6, this.bl, true);
            var1.eol();
            return var7;
         }
      }
   }

   private void q(bat var1, ISegmentInformation var2, boolean var3, ISegmentInformation var4) {
      StringBuilder var5 = new StringBuilder(var3 ? "Segment" : "Section");
      var5.append(":");
      String var6 = var2.getName();
      String var7 = Formatter.toHexString(var2.getSizeInMemory(), true);
      String var8 = "";
      if (var6 != null) {
         var5.append("\"").append(var6).append("\" ");
      }

      if (var4 != null) {
         int var9 = var6 == null ? 0 : var6.length();
         int var10 = var4.getName() == null ? 0 : var4.getName().length();
         if (var10 > var9) {
            var5.append(Strings.spaces(var10 - var9));
         }

         int var11 = var7.length();
         int var12 = Formatter.toHexString(var4.getSizeInMemory(), true).length();
         if (var12 > var11) {
            var8 = Strings.spaces(var12 - var11);
         }
      }

      var5.append("Size:").append(var8).append(var7).append("h ");
      String var13 = q(var2.getFlags());
      if (!var13.isEmpty()) {
         var5.append("Permissions:").append(var13);
      }

      this.q(var1, var5.toString(), this.bl, true);
   }

   private static String q(int var0) {
      StringBuilder var1 = new StringBuilder();
      if ((var0 & 2) != 0) {
         if (var1.length() > 0) {
            var1.append(",");
         }

         var1.append("READ");
         var0 &= -3;
      }

      if ((var0 & 1) != 0) {
         if (var1.length() > 0) {
            var1.append(",");
         }

         var1.append("WRITE");
         var0 &= -2;
      }

      if ((var0 & 4) != 0) {
         if (var1.length() > 0) {
            var1.append(",");
         }

         var1.append("EXECUTE");
         var0 &= -5;
      }

      if ((var0 & 1073741824) != 0) {
         if (var1.length() > 0) {
            var1.append(",");
         }

         var1.append("ALLOCATE ON WRITE");
      }

      return var1.toString();
   }

   void q(bat var1) {
      String var2 = this.Yp.generateHeader();
      if (var2 != null) {
         this.q(var1, var2, null);
         var1.eol();
      }

      if (!this.CY.isAnalysisCompleted()) {
         var1.eol();
         var2 = S.L("An analysis pass is ongoing, please wait...");
         this.q(var1, var2, false, true);
      } else if (!this.CY.isInitialAnalysisDone()) {
         var1.eol();
         var2 = S.L(
            "The initial analysis has not been performed. How to start one:\n- GUI: in the 'Native Code' menu, via the command 'Start or resume an Analysis'\n- API: via the INativeCodeUnit.performInitialAnalysis() method"
         );
         this.q(var1, var2, null);
         var1.eol();
      }
   }

   void RF(bat var1, String var2) {
      var1.appendAndRecord(var2, ItemClassIdentifiers.KEYWORD);
   }

   String RF(long var1, boolean var3) {
      return this.CY.q(var1, var3 ? AutoLabelPolicy.ON : AutoLabelPolicy.OFF);
   }

   private void RF(bat var1, long var2) {
      this.q(var1, var2, null, 0, 0, null, -1, true);
   }

   private void RF(bat var1, long var2, boolean var4) {
      if (this.Lj) {
         this.q(var1, var2, var4);
      }
   }

   public void q(bat var1, long var2, byte[] var4, int var5, int var6, INativeMemoryItem var7, int var8, boolean var9) {
      if (var1.getCurrentLineLength() != 0) {
         throw new RuntimeException(Strings.ff("Line not empty: \"%s\"", Formatter.escapeString(var1.getCurrentLine().getText())));
      } else {
         boolean var10 = var4 == null && var8 < 0 && var9;
         this.RF(var1, var2, !var10);
         if (var10) {
            var1.eol();
         } else {
            if (this.nv > 0) {
               this.q(var1, var4, var5, var6);
            }

            if (var7 != null) {
               var2 = var7.getMemoryAddress();
            }

            this.q(var1, var2, var7, var8, var9);
         }
      }
   }

   boolean q(bat var1, ICodeCoordinates var2) {
      Comment var3 = this.CY.getCommentManager().getComment2(var2);
      if (var3 == null) {
         return false;
      } else {
         CommentGenerator var4 = new CommentGenerator(var1, this.Yp.getInlineCommentString());
         var4.setMargin(this.Qu.length());
         return var4.genPre(var2, var3, null);
      }
   }

   boolean RF(bat var1, ICodeCoordinates var2) {
      String var3 = this.CY.getCommentManager().getPrimary2(var2, 0);
      if (var3 == null) {
         return false;
      } else {
         this.q(var1, var3, this.jh);
         return true;
      }
   }

   public void q(bat var1, long var2, ICodeCoordinates var4, String var5, boolean var6) {
      Comment var7 = this.CY.getCommentManager().getComment2(var4);
      CommentGenerator var8 = new CommentGenerator(var1, this.Yp.getInlineCommentString());
      var8.setMargin(this.jh.length());
      var8.genInline(var4, var7, 0, 0, var5);
      if (var6) {
         var1.eol();
      }
   }

   private String q(INativeDataItem var1, long var2) {
      StringBuilder var4 = new StringBuilder();
      long var5 = var1.getMemoryAddress();
      Set var7 = null;
      if (var5 == var2) {
         var7 = this.Gu.HF().getReferencesTo(var5);
      } else if (var1 instanceof axr) {
         var7 = this.Gu.HF().getReferencesTo(var2);
      } else if (!(var1 instanceof axq) && var2 < var5 + 16L) {
         var7 = this.Gu.HF().getReferencesTo(var5);
      }

      if (var7 != null && !var7.isEmpty()) {
         this.q(var7, var4);
      }

      return var4.toString();
   }

   private void q(Set var1, StringBuilder var2) {
      StringBuilder var3 = new StringBuilder();
      int var4 = 0;
      byte var5 = 0;

      for (abk var7 : var1) {
         if (var7.getFrom().isInternalAddress()) {
            if (this.YA >= 0 && var4 >= this.YA) {
               break;
            }

            long var8 = var7.getFrom().getInternalAddress();
            if (var4 >= 1) {
               var3.append(" / ");
            }

            String var10 = this.CY.getSymbolicStringAddress(var8, 2);
            var3.append(var10);
            var3.append(" (");
            var3.append(var7.getStringType());
            var3.append(")");
            var4++;
         }
      }

      if (var4 > 0) {
         if (var2.length() > 0) {
            var2.append('\n');
         }

         var2.append("xref: ");
         var2.append((CharSequence)var3);
         int var11 = var1.size() - var4 - var5;
         if (var11 > 0) {
            var2.append(" (").append(var11).append(" more)");
         }
      }
   }

   private String q(axn var1) {
      StringBuilder var2 = new StringBuilder();
      long var3 = var1.getMemoryAddress();
      long var5 = var3 + var1.getMemorySize();
      qz var7 = this.Gu.HF(var3);
      if (!var7.isEmpty()) {
         int var8 = 0;
         short var9 = 160;
         short var10 = 320;
         int var11 = 0;

         for (BranchTarget var13 : var7.getTargets()) {
            if (var13.isInternal()) {
               if (var8 == 0) {
                  var2.append("-> ");
               } else if (var8 >= 1) {
                  var2.append(", ");
               }

               long var14 = var13.getInternalAddress().getAddress();
               if (var13 instanceof abt) {
                  var2.append("[");
                  int var16 = -1;
                  int var17 = -1;

                  for (int var19 : ((abt)var13).q()) {
                     if (var16 == -1) {
                        var16 = var19;
                     } else if (var17 == -1) {
                        if (var16 + 1 == var19) {
                           var17 = var19;
                        } else {
                           var2.append(var16).append("; ");
                           var16 = var19;
                        }
                     } else if (var17 + 1 == var19) {
                        var17 = var19;
                     } else {
                        if (var16 + 1 == var17) {
                           var2.append(var16).append("; ");
                           var2.append(var17).append("; ");
                        } else {
                           var2.append(var16).append("-").append(var17).append("; ");
                        }

                        var16 = var19;
                        var17 = -1;
                     }

                     if (var2.length() - var11 > var10) {
                        var2.append("\n");
                        var11 = var2.length();
                     }
                  }

                  if (var17 == -1) {
                     var2.append(var16);
                  } else if (var16 + 1 == var17) {
                     var2.append(var16).append("; ");
                     var2.append(var17);
                  } else {
                     var2.append(var16).append("-").append(var17);
                  }

                  var2.append("] => ");
               }

               var2.append(this.RF(var14, true));
               var8++;
            }

            if (var2.length() - var11 > var9) {
               var2.append("\n");
               var11 = var2.length();
            }
         }
      }

      CharSequence var20 = this.Yp.generateExtraComment(var3, var1.getInstruction());
      if (var20 != null) {
         if (var2.length() != 0) {
            var2.append("\n");
         }

         var2.append(var20);
      }

      abn var21 = this.Gu.LK(var3);
      if (!var21.isEmpty()) {
         this.q(var2, var21, false, var5);
         this.q(var2, var21, true, var5);
      }

      Set var22 = this.Gu.HF().getReferencesTo(var3);
      if (!var22.isEmpty()) {
         StringBuilder var23 = new StringBuilder();
         int var25 = 0;
         int var26 = 0;

         for (abk var15 : var22) {
            if (var15.getFrom().isInternalAddress()) {
               if (this.YA >= 0 && var25 >= this.YA) {
                  break;
               }

               long var29 = var15.getFrom().getInternalAddress();
               INativeContinuousItem var30 = this.Gu.getItemAt(var29);
               if (var30 != null && var15.getType() == ReferenceType.COND_BRANCH && (Long)var30.getEnd() == var3) {
                  var26++;
               } else if (var30 != null && (Long)var30.getBegin() == var3) {
                  var26++;
               } else {
                  if (var25 >= 1) {
                     var23.append(" / ");
                  }

                  String var31 = this.CY.getSymbolicStringAddress(var29, 2);
                  var23.append(var31);
                  var23.append(" (");
                  var23.append(var15.getStringType());
                  var23.append(")");
                  var25++;
               }
            }
         }

         if (var25 > 0) {
            if (var2.length() > 0) {
               var2.append('\n');
            }

            var2.append("xref: ");
            var2.append((CharSequence)var23);
            int var28 = var22.size() - var25 - var26;
            if (var28 > 0) {
               var2.append(" (").append(var28).append(" more)");
            }
         }
      }

      int var24 = var1.Uv();
      if (var24 == 2) {
         var2.append("\nswitch dispatcher");
      }

      return var2.toString();
   }

   private void q(StringBuilder var1, abn var2, boolean var3, long var4) {
      int var6 = 0;

      for (Entry var8 : var2.getAll(var3).entrySet()) {
         long var9 = (Long)var8.getKey();
         String var11 = this.Yp.getRegisterName(var9);
         List var12 = (List)var8.getValue();
         if (!Strings.equals(var11, "LR") || var12.size() != 1 || (Long)var12.get(0) != var4) {
            if (var6 == 0) {
               if (var1.length() > 0) {
                  var1.append(" /");
               }

               var1.append(var3 ? "POST: " : "PRE: ");
            } else if (var6 >= 1) {
               var1.append(" / ");
            }

            if (var11 == null) {
               var11 = "r" + var9;
            }

            var1.append(var11 + "=");
            if (var12.size() >= 2) {
               var1.append('{');
            }

            int var13 = 0;

            for (long var15 : var12) {
               if (var13 >= 1) {
                  var1.append(", ");
               }

               var1.append(this.RF(var15, true));
               INativeContinuousItem var17 = this.Gu.getItemAt(var15);
               if (var17 instanceof INativeStringItem) {
                  String var18 = ((INativeStringItem)var17).getValue();
                  if (var18 != null && !var18.isEmpty()) {
                     var1.append("(\"").append(Formatter.escapeString(var18)).append("\")");
                  }
               }

               var13++;
            }

            if (var12.size() >= 2) {
               var1.append('}');
            }

            var6++;
         }
      }
   }

   @Override
   public bax q(byte[] var1, int var2, INativeDataItem var3, bat var4, Deque var5) {
      if (var3 instanceof axq) {
         return new bar(var1, var2, (axq)var3, var4, this.Yp, this, var5);
      } else if (var3 instanceof axr) {
         return new bau(var1, var2, (axr)var3, var4, this.Yp, this, var5);
      } else {
         return (bax)(!(var3 instanceof axv) && !(var3 instanceof axu) ? new bbc(var4) : new bbb(var1, var2, var3, var4, this.Yp, this, var5));
      }
   }

   @Override
   public INativeCodeUnit getUnit() {
      return this.CY;
   }

   @Override
   public boolean hasBinaryRepresentation() {
      return true;
   }

   @Override
   public IBinaryRepresentation getBinaryRepresentation() {
      return new bay.eo();
   }

   static {
      Assert.a("SLACK".length() <= 9);
   }

   @SerDisabled
   private class CU implements ISegment {
      Long q;
      Long RF;
      ISegmentInformation xK;
      String Dw;

      CU(ISegmentInformation var2, String var3) {
         if (var2 == null) {
            throw new NullPointerException();
         } else {
            this.xK = var2;
            this.q = bay.this.CY.getVirtualImageBase() + var2.getOffsetInMemory();
            this.RF = this.q + var2.getSizeInMemory();
            if (!bay.this.CY.RF(this.q, this.RF - 1L)) {
               throw new JebRuntimeException("Illegal segment");
            } else if (var3 == null) {
               throw new NullPointerException();
            } else {
               this.Dw = var3;
            }
         }
      }

      public Long q() {
         return this.q;
      }

      public Long RF() {
         return this.RF;
      }
   }

   private class eo implements IBinaryRepresentation {
      eo() {
      }

      @Override
      public long getBaseOffsetHint() {
         return 0L;
      }

      @Override
      public int read(long var1, int var3, byte[] var4, int var5) {
         return VirtualMemoryUtil.readBytesSafe(bay.this.WI, var1, var3, var4, var5, true);
      }

      @Override
      public long find(long var1, long var3, byte[] var5, byte[] var6) {
         return VirtualMemoryUtil.findBytes(bay.this.WI, true, var1, var3, var5, var6);
      }

      @Override
      public long convertCoordinatesToOffset(ICoordinates var1) {
         String var2 = bay.this.coordinatesToAddress(var1);
         return var2 == null ? -1L : bay.this.CY.getCanonicalMemoryAddress(var2);
      }

      @Override
      public ICoordinates convertOffsetToCoordinates(long var1) {
         String var3 = bay.this.CY.getSymbolicStringAddress(var1);
         return var3 == null ? null : bay.this.addressToCoordinates(var3);
      }
   }
}
