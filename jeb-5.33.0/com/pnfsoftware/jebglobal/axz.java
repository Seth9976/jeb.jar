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
import com.pnfsoftware.jeb.core.units.code.asm.items.DataHints;
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
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class axz extends CodeDocument implements INativeDisassemblyDocument {
   private static final ILogger pC = GlobalLog.getLogger(axz.class);
   private boolean A;
   private IPropertyManager kS;
   private C wS;
   private IVirtualMemory UT;
   private MemoryRanges E;
   private GenericCodeFormatter sY;
   private Tw ys;
   private IEventListener ld;
   private IEventListener gp;
   private int oT = "SLACK".length();
   private AddressSegmentMap fI;
   private AddressSegmentMap WR;
   private NativeDisassemblyProperties NS;
   private boolean vP = true;
   private int xC = 0;
   private boolean ED = true;
   private String Sc = ".";
   private int ah = 80;
   private boolean eP = true;
   private boolean UO = false;
   private boolean Ab = true;
   private String rl = "=";
   private String z = "-";
   private int Ek = 80;
   private boolean hK = false;
   private boolean Er = true;
   private int FE = 16;
   private int Aj = 40;
   private int EX = 50;
   private boolean LM = false;
   private boolean mv = true;
   private boolean sO = false;
   private int os = 16;
   private int Cu = 1;
   private String hZ = "'";
   private String UW;
   private String PR;
   private String cX;
   private int DQ = 100;
   private int ZN = 100;

   public axz(C var1) {
      this.wS = var1;
      this.sY = var1.getCodeFormatter();
      this.ys = (Tw)var1.getCodeModel();
      this.fI = new AddressSegmentMap(this.ys.getBitsize());
      this.WR = new AddressSegmentMap(this.ys.getBitsize());
      this.UT = var1.getMemory();
      this.E = new MemoryRanges(this.UT);
      this.pC(false);
      var1.addListener(this.ld = new aya(this));
      this.kS = var1.getPropertyManager();
      this.kS.addListener(this.gp = new ayb(this));
   }

   @Override
   public void dispose() {
      if (!this.A) {
         super.dispose();
         this.wS.removeListener(this.ld);
         this.kS.removeListener(this.gp);
         this.A = true;
      }
   }

   @Override
   public long getInitialAnchor() {
      if (this.wS.isInitialAnalysisDone()) {
         long var1 = this.wS.getHighLevelEntryPointAddress();
         if (var1 != -1L) {
            return this.pC(var1);
         }

         if (this.wS.getParent() instanceof ICodeObjectUnit) {
            ICodeObjectUnit var3 = (ICodeObjectUnit)this.wS.getParent();
            var1 = this.wS.getVirtualImageBase() + var3.getLoaderInformation().getEntryPoint();
            return this.pC(var1);
         }
      }

      return super.getInitialAnchor();
   }

   @Override
   public long getAnchorCount() {
      return this.E.aggregatedRangesSize();
   }

   public long pC(long var1) {
      return this.pC(var1, false);
   }

   public long pC(long var1, boolean var3) {
      long var4 = 0L;

      for (Couple var7 : this.E.asList()) {
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

   public long A(long var1) {
      long var3 = 0L;

      for (Couple var6 : this.E.asList()) {
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
      long var4 = this.A(var1.getAnchorId());
      if (this.wS.A(var4) != null) {
         int var6 = var1.getLineDelta();

         try {
            INativeContinuousItem var7 = this.ys.getItemAt(var4);
            if (var4 == this.wS.getVirtualImageBase()) {
               axv var8 = new axv();
               this.pC(var8);
               var6 -= var8.getLineCount();
            }

            axv var17 = new axv();
            this.pC(var17, var4);
            var6 -= var17.getLineCount();
            var6 -= this.A(var7);
            if (var6 < 0) {
               var3 = 1;
            }
         } catch (Exception var11) {
            pC.catchingSilent(var11);
         }
      } else if (var1.getColumnOffset() != 0 && this.xC > 0) {
         int var14 = var1.getLineDelta();

         try {
            INativeContinuousItem var16 = this.ys.getItemOverOrGap(var4, var4, var4 + 32L);
            if (var4 == this.wS.getVirtualImageBase()) {
               axv var18 = new axv();
               this.pC(var18);
               var14 -= var18.getLineCount();
            }

            axv var19 = new axv();
            this.pC(var19, var4);
            var14 -= var19.getLineCount();
            if (var4 == var16.getMemoryAddress()) {
               var14 -= this.A(var16);
            }

            if (var14 >= 0) {
               int var20 = var1.getColumnOffset() - this.pC() + 1;
               if (var20 > 0) {
                  int var9 = var20 / 3;
                  if (var9 < this.xC) {
                     if (var16 instanceof aus) {
                        if (var9 < var16.getMemorySize()) {
                           var4 += var9;
                        }
                     } else if (var16 instanceof auxx) {
                        if (var9 < Math.min((long)this.xC, (Long)var16.getEnd() - var4)) {
                           var4 += var9;
                        }
                     } else if (var16 instanceof INativeDataItem && var9 < var16.getMemorySize()) {
                        var4 += var9;
                     }
                  }
               }
            }
         } catch (Exception var10) {
            pC.catchingSilent(var10);
         }
      }

      return this.wS.getSymbolicStringAddress(var4, var3);
   }

   private int pC() {
      axv var1 = new axv();
      this.A(var1, 0L, true);
      return var1.getCurrentLineLength();
   }

   private int A(INativeContinuousItem var1) {
      if (var1 instanceof aus) {
         axv var4 = new axv();
         this.pC(var4, (aus)var1, var1.getMemoryAddress());
         return var4.getLineCount();
      } else if (var1 instanceof auxx) {
         axv var3 = new axv();
         return var3.getLineCount();
      } else if (var1 instanceof INativeDataItem) {
         axv var2 = new axv();
         this.pC(var2, (INativeDataItem)var1);
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

      long var3 = this.wS.getCanonicalMemoryAddress(var1);
      if (var3 == -1L) {
         return null;
      } else {
         if (this.wS.getPhysicalImageDelta() != 0L
            && (
               Longs.compareUnsigned(var3, this.wS.getVirtualImageBase()) < 0
                  || Longs.compareUnsigned(var3, this.wS.getVirtualImageBase() + this.wS.getImageSize()) > 0
            )) {
            var3 -= this.wS.getPhysicalImageDelta();
         }

         long var5 = this.pC(var3);
         if (var5 == -1L) {
            return null;
         } else {
            INativeContinuousItem var7 = this.ys.getItemOver(var3);
            if (var7 != null) {
               boolean var8 = var7 instanceof INativeInstructionItem;
               axv var9 = new axv(0L, this);
               this.pC(var9, var7, 10, -1L, -1L, -1, -1);
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
                           var17 = Integer.min((var8 ? this.PR : this.UW).length(), var15.getText().length());
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

   public axv pC(long var1, long var3) {
      long var5 = this.E.min();
      long var7 = this.E.max();
      long var9 = this.E.getLocalBegin(var1);
      long var11 = this.E.getLocalEnd(var1);
      if (Longs.compareUnsigned(var1, var5) >= 0 && Longs.compareUnsigned(var1, var7) < 0) {
         aul var13 = this.pC(var1, var9, var11);
         if (var13 == null) {
            return null;
         } else {
            long var14 = var13.getMemoryAddress();
            axv var16 = new axv(0L, this);
            this.pC(var16, var14, var1, -1, -1, var3, var5, var7);
            return var16;
         }
      } else {
         return null;
      }
   }

   public axv pC(long var1, int var3, int var4) {
      long var5 = this.E.min();
      long var7 = this.E.max();
      long var9 = this.A(var1);
      long var11 = this.E.getLocalBegin(var9);
      long var13 = this.E.getLocalEnd(var9);
      if (Longs.compareUnsigned(var9, var5) >= 0 && Longs.compareUnsigned(var9, var7) < 0) {
         aul var15 = this.pC(var9, var11, var13);
         if (var15 == null) {
            return null;
         } else {
            INativeContinuousItem var16 = this.kS(var9, true);
            if (var16 != null) {
               var9 = var16.getMemoryAddress();
               var15 = (aul)var16;
            }

            Object[] var10000 = new Object[]{var4, var9, var3};
            if (var4 < Integer.MAX_VALUE - this.DQ) {
               var4 += this.DQ;
            }

            if (var3 < Integer.MAX_VALUE - this.ZN) {
               var3 += this.ZN;
            }

            long var17 = var15.getMemoryAddress();
            axv var19 = new axv(0L, this);
            this.pC(var19, var17, var9, var4, var3, -1L, var5, var7);
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
                  Couple var25 = this.E.getPreviousRange(var17);
                  if (var25 == null) {
                     break;
                  }

                  var11 = Longs.maxUnsigned(var5, (Long)var25.getFirst());
                  Longs.minUnsigned(var7, (Long)var25.getSecond());
                  var17 = (Long)var25.getSecond();
                  var9 = Math.max(var11, var17 - var24);
               }

               axv var26 = new axv(0L, this);
               if (!this.pC(var26, var9, -1L, var4, -1, var17, var5, var7) || var26.getLineCount() == 0) {
                  break;
               }

               var20 += var26.getLineCount();
               var17 = this.A(var26.getAnchor(0).getIdentifier());
               var19.prependCodePart(var26);
            }

            return var19;
         }
      } else {
         return null;
      }
   }

   boolean pC(axv var1, long var2, long var4, int var6, int var7, long var8, long var10, long var12) {
      int var14 = 0;
      boolean var15 = true;
      Couple var16 = this.E.getLocalRange(var2);
      Assert.a(var16 != null, "Cannot find range");
      long var17 = (Long)var16.getFirst();
      long var19 = (Long)var16.getSecond();
      long var21 = Longs.maxUnsigned(var17, var10);
      long var23 = Longs.minUnsigned(var19, var12);
      long var25 = -1L;

      while (true) {
         if (var2 >= var19) {
            var16 = this.E.getNextRange(var2);
            if (var16 == null) {
               break;
            }

            var17 = (Long)var16.getFirst();
            var19 = (Long)var16.getSecond();
            var2 = var17;
            var21 = Longs.maxUnsigned(var17, var10);
            var23 = Longs.minUnsigned(var19, var12);
         }

         aul var27 = this.pC(var2, var21, var23);
         if (var27 == null) {
            return false;
         }

         int var28 = var1.getLineCount();
         if (var14 == 0 && (var4 == -1L || Longs.compareUnsigned(var2, var4) >= 0)) {
            var14 = var28;
            if (var1.getLineCount() > 1 && var4 >= 0L) {
               long var29 = this.pC(var4);
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
         long var36 = this.pC(var1, var27, -1, var25, var4, var6, var7);
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
         var1.registerAnchor(this.pC(var2, true), "a_" + var2);
      }

      return var15;
   }

   private aul pC(long var1, long var3, long var5) {
      aul var7 = (aul)this.ys.getItemOver(var1);
      if (var7 == null) {
         long var8 = var1 & -256L;
         long var10 = var8 + 256L;
         if (Longs.compareUnsigned(var3, var8) > 0) {
            var8 = var3;
         }

         if (Longs.compareUnsigned(var5, var10) < 0) {
            var10 = var5;
         }

         var7 = (aul)this.ys.getItemOverOrGap(var1, var8, var10);
         if (var7 instanceof auxx) {
            var8 = var7.kS();
            var10 = var7.wS();
            axz.Sv var12 = (axz.Sv)this.fI.getSegmentContaining(var1);
            if (var12 != null) {
               var8 = Math.max(var8, var12.pC);
               var10 = Math.min(var10, var12.A);
            } else {
               axz.Sv var13 = (axz.Sv)this.fI.getSegmentBefore(var1);
               if (var13 != null) {
                  var8 = Math.max(var8, var13.A);
               }

               var13 = (axz.Sv)this.fI.getSegmentAfter(var1);
               if (var13 != null) {
                  var10 = Math.min(var10, var13.pC);
               }
            }

            var12 = (axz.Sv)this.WR.getSegmentContaining(var1);
            if (var12 != null) {
               var8 = Math.max(var8, var12.pC);
               var10 = Math.min(var10, var12.A);
            } else {
               axz.Sv var18 = (axz.Sv)this.WR.getSegmentBefore(var1);
               if (var18 != null) {
                  var8 = Math.max(var8, var18.A);
               }

               var18 = (axz.Sv)this.WR.getSegmentAfter(var1);
               if (var18 != null) {
                  var10 = Math.min(var10, var18.pC);
               }
            }

            if (var7.kS() == var8 && var7.wS() == var10) {
               return var7;
            }

            var7 = (aul)this.ys.getGapFactory().create(var8, var10);
         }
      }

      return var7;
   }

   public String pC(INativeContinuousItem var1) {
      axv var2 = new axv();
      this.pC(var2, var1, -1, -1L, -1L, -1, -1);
      return var2.format();
   }

   long pC(axv var1, INativeContinuousItem var2, int var3, long var4, long var6, int var8, int var9) {
      long var10 = var2.getMemoryAddress();
      int var12 = this.pC(var2, var6, var8);
      long var13 = -1L;
      if (var12 <= 0) {
         long var15 = this.pC(var10);
         var1.registerAnchor(var15, "a_" + var10);
         if (var10 == this.wS.getVirtualImageBase()) {
            this.pC(var1);
         }

         var13 = this.pC(var1, var10);
         if (var13 == -1L && var4 != -1L && var4 != var2.getMemoryAddress()) {
            this.pC(var1, "...", false, true);
            this.pC(var1, "... " + S.L("(not allocated)"), false, true);
            this.pC(var1, "...", false, true);
         }
      }

      if (var2 instanceof aus) {
         return this.pC(var1, (aus)var2);
      } else if (var2 instanceof auxx) {
         INativeContinuousItem var18 = this.kS(var10, false);
         if (var18 != null) {
            long var16 = this.pC(var1, var18, var3, var4, var13, var8, var9);
            return Math.max(var16, var10 + 1L);
         } else {
            return this.pC(var1, (auxx)var2, var3, var13);
         }
      } else if (var2 instanceof INativeDataItem) {
         return this.pC(var1, (INativeDataItem)var2, var3, var6, var12, var9);
      } else {
         throw new RuntimeException("Unsupported continuous item type: " + var2.getClass().getName());
      }
   }

   ICodeCoordinates pC(axv var1, aus var2, long var3) {
      boolean var6 = this.ys.isRoutineHeader(var3);
      auu var5;
      if (!var6) {
         var5 = this.ys.kS(var3, false);
      } else {
         var5 = this.wS.A(var3);
         aut var7 = var5 == null ? null : var5.E();
         if (var7 == null) {
            Assert.debugFail(Strings.ff("Expecting internal routine at address %Xh", var3));
         } else {
            this.pC(var1, var3, var5, var7);
            MethodCoordinates var8 = new MethodCoordinates(var5.getIndex());
            this.pC(var1, var8);
            this.pC(var1, var3, null, 0, 0, var7, 1, false);
            this.A(var1, this.sY.getProcedureDefinitionStart());
            this.A(var1, var8);
            var1.eol();
            this.A(var1, var3);
         }
      }

      Object var14;
      if (var5 != null) {
         var14 = new InstructionCoordinates(var5.getIndex(), (int)(var3 - var5.getMemoryAddress()));
      } else {
         var14 = new NativeCoordinates(var3);
      }

      String var15 = this.A(var3, false);
      boolean var9 = false;
      boolean var10 = this.ys.isBasicBlockHeader(var3);
      if (var10 && var15 == null) {
         BasicBlock var11 = this.ys.getBasicBlockHeader(var3);
         if (var11 != null) {
            for (BasicBlock var13 : var11.getInputs()) {
               if (var13.getEndAddress() != var3) {
                  var9 = true;
                  break;
               }
            }
         }
      }

      boolean var16 = false;
      if (!var6 && (var15 != null || var9)) {
         this.pC(var1, var3, null, 0, 0, null, 1, true);
         var16 = true;
      }

      if (var14 instanceof InstructionCoordinates) {
         var16 |= this.pC(var1, (ICodeCoordinates)var14);
      }

      if (var10 && this.LM && (var5 == null || var5.getMemoryAddress() != var3) && !var16) {
         this.A(var1, var3);
      }

      return (ICodeCoordinates)var14;
   }

   private void pC(axv var1, long var2, auu var4, aut var5) {
      var1.eol();
      var1.eol();
      if (this.Ab) {
         if (this.Ek > 0) {
            this.pC(var1, Strings.generate(this.rl, ((this.hK ? this.PR.length() - 2 : 0) + this.Ek) / this.rl.length()), this.Er, true);
         }

         for (auu var7 : var5.getMethodReferences()) {
            ayi var8 = var7.ys();
            StringBuilder var9;
            if (var8 == null) {
               var9 = new StringBuilder(S.L("ROUTINE") + ": ").append(var7.getName(true));
            } else {
               var9 = new StringBuilder(S.L("METHOD") + ": ").append(var8.getName(true)).append("::").append(var7.getName(true));
            }

            this.pC(var1, var9, this.Er, true);
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
            this.pC(var1, var25.toString(), this.Er, true);
         }

         CFG var24 = var5.getCFG();
         long var26 = var24.getFirstAddress();
         if (Longs.compareUnsigned(var2, var26) > 0) {
            this.pC(var1, Strings.ff(S.L("(Routine entry is located after routine lowest address %Xh)"), var26), this.Er, true);
         }

         List var28 = var24.getExitBlocks();
         int var29 = var28.size();
         if (var29 != 1) {
            if (var29 == 0) {
               this.pC(var1, S.L("(Routine has no exit node)"), this.Er, true);
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
               this.pC(var1, var12.toString(), this.Er, true);
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
            this.pC(var1, var31.toString(), this.Er, true);
         }

         Boolean var32 = var4.getNonReturning();
         if (var32 != null && var32) {
            this.pC(var1, S.L("(Routine does not return)"), this.Er, true);
         }

         if (var4.Ek()) {
            this.pC(var1, S.L("(Routine's name comes from a caller identified as library code)"), this.Er, true);
         }

         if (var5.E() != null) {
            if (var5.E().E() != null) {
               String var34 = Strings.ff(S.L("(Trampoline to internal routine at %Xh: %s)"), var5.E().getMemoryAddress(), var5.E().getAddress());
               this.pC(var1, var34, this.Er, true);
            } else {
               String var35 = Strings.ff(S.L("(Trampoline to external routine %s)"), var5.E().getAddress());
               this.pC(var1, var35, this.Er, true);
            }
         }

         if (var4.UT() != null) {
            String var36 = var4.getSignature(true);
            this.pC(var1, "", this.Er, true);
            this.pC(var1, S.L("Signature") + ": " + var36, this.Er, true);
         }

         String var37 = var4.sO();
         if (var37 != null) {
            this.pC(var1, "", this.Er, true);
            this.pC(var1, S.L("DWARF Signature") + ": " + var37, this.Er, true);
         }

         String var39 = var4.FE();
         if (var39 != null) {
            this.pC(var1, "", this.Er, true);
            this.pC(var1, S.L("Demangled declaration") + ": " + var39, this.Er, true);
         }

         String var40 = var4.Aj();
         if (var40 != null && var39 == null) {
            this.pC(var1, "", this.Er, true);
            this.pC(var1, S.L("Demangled name") + ": " + var40, this.Er, true);
         }

         if (var40 != null || var39 != null) {
            String var17 = var4.Er();
            if (var17 != null) {
               this.pC(var1, "| " + S.L("original name") + ": " + var17, this.Er, true);
            }
         }

         com.pnfsoftware.jeb.corei.parsers.asm.nativesig.HE var41 = var4.z();
         if (var41 != null) {
            INativeSignature var18 = var41.pC();
            if (var18 != null) {
               this.pC(var1, "", this.Er, true);
               this.pC(var1, S.L("Best matching routine signature:"), this.Er, true);
               this.pC(var1, var18);
            }

            List var19 = var41.A();
            if (var19 != null) {
               this.pC(var1, "", this.Er, true);
               this.pC(var1, S.L("Alternate matching routine signatures:"), this.Er, true);

               for (INativeSignature var21 : var19) {
                  this.pC(var1, var21);
               }
            }
         }

         com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.Sv var42 = var4.hK();
         if (var42 != null) {
            if (var42.pC() != null) {
               this.pC(var1, "", this.Er, true);
               this.pC(var1, S.L("Codeless signature match") + ": " + var42.pC().toString(), this.Er, true);
            } else if (var42.A() != null) {
               this.pC(var1, "", this.Er, true);
               this.pC(var1, Strings.ff(S.L("Codeless signature possible matches (%d): %s"), var42.A().size(), var42.A().toString()), this.Er, true);
            }
         }

         String var43 = this.sY.generateExtraMethodComment(var2);
         if (var43 != null) {
            this.pC(var1, var43, this.Er, true);
         }

         var1.eol();
      }
   }

   private void pC(axv var1, INativeSignature var2) {
      boolean var3 = false;
      if (var2.getFlags().hasMeaningfulTargetName()) {
         this.pC(var1, "| " + S.L("name:") + var2.getTargetName(), this.Er, true);
         if (var2.getAlternateNames() != null && var2.getAlternateNames().size() != 0) {
            this.pC(var1, "| " + S.L("alternate names:") + Strings.join(",", var2.getAlternateNames()), this.Er, true);
         }

         var3 = true;
      } else if (var2.getPossibleNames() != null && var2.getPossibleNames().size() != 0) {
         this.pC(var1, "| " + S.L("possible names:") + Strings.join(",", var2.getPossibleNames()), this.Er, true);
         var3 = true;
      }

      if (var3) {
         Set var4 = ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var2).kS();
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

            this.pC(var1, var5.toString(), this.Er, true);
         }

         if (var2.getAttributes() != null) {
            for (INativeAttribute var10 : var2.getAttributes()) {
               if (!(var10 instanceof aww) && var10.isPrintable()) {
                  this.pC(var1, "| " + var10.toString(), this.Er, true);
               }
            }
         }
      }
   }

   private aut kS(long var1) {
      List var3 = this.ys.getContainedRoutineAddresses(var1);
      if (var3.isEmpty()) {
         return null;
      } else {
         aut var4 = null;

         for (long var6 : var3) {
            auu var8 = this.wS.A(var6);
            aut var9 = var8 == null ? null : var8.E();
            if (var9 == null) {
               Assert.debugFail(Strings.ff("Expecting internal routine at address %Xh", var1));
            } else {
               if (var1 == var9.getCFG().getLastAddress()) {
                  return var9;
               }

               if (this.Ek > 0 && var4 == null) {
                  var4 = var9;
               }
            }
         }

         return var4;
      }
   }

   void A(axv var1, aus var2, long var3) {
      aut var5 = this.kS(var3);
      if (var5 != null) {
         if (var3 == var5.getCFG().getLastAddress()) {
            this.A(var1, var3);
            this.pC(var1, var3, null, 0, 0, var5, 1, false);
            this.A(var1, this.sY.getProcedureDefinitionEnd());
            var1.eol();
            if (this.Ek > 0 && this.Ab) {
               this.pC(var1, Strings.generate(this.z, ((this.hK ? this.PR.length() - 2 : 0) + this.Ek) / this.z.length()), this.Er, true);
               var1.eol();
            }
         } else if (this.Ek > 0 && this.Ab) {
            long var6 = var2.getMemoryAddress() + var2.getMemorySize();
            if (!this.ys.getContainedRoutineAddresses(var6).contains(var5.getMemoryAddress())) {
               this.pC(var1, Strings.generate(this.z, ((this.hK ? this.PR.length() - 2 : 0) + this.Ek) / this.z.length()), this.Er, true);
            }
         }
      }
   }

   long pC(axv var1, aus var2) {
      long var3 = var2.getMemoryAddress();
      ICodeCoordinates var5 = this.pC(var1, var2, var3);
      IInstruction var6 = var2.getInstruction();
      byte[] var7 = var6.getCode();
      this.pC(var1, var3, var7, 0, var7.length, var2, -1, false);
      this.sY.formatInstruction(var3, var6, var1);
      String var8 = this.pC(var2);
      this.pC(var1, var3, var5, var8, true);
      this.A(var1, var2, var3);
      return var2.getMemoryAddressEnd();
   }

   void pC(axv var1, INativeDataItem var2) {
      if (var2 instanceof auw) {
         INativeType var3 = var2.getType();
         this.pC(var1, var2.getMemoryAddress(), null, 0, 0, var2, -1, false);
         this.pC(var1, this.PR);
         this.pC(var1, var3.getName(true), false, true);
      }
   }

   private int pC(INativeContinuousItem var1, long var2, int var4) {
      int var5 = 0;
      if (var4 > 0 && var1 instanceof auv && !(var1 instanceof avb)) {
         auv var6 = (auv)var1;
         if (var6.rl() > 1000) {
            INativeType var7 = TypeUtil.getNonAlias(var6.z());
            double var8 = var7 instanceof ayv ? 1.0 / ((ayv)var7).getFieldsCount() : this.sY.getArrayElementPerLine();
            long var10 = var1.getMemoryAddress();
            if (var2 == -1L) {
               var5 = var6.rl() - (int)(var4 * var8) * 3;
               return var5 >= 0 ? var5 : 0;
            }

            long var12 = var2 - var10;
            if (var12 > 0L && var12 < 2147483647L) {
               int var14 = var7.getSize();
               int var15 = (int)(var12 / var14);
               if (var15 < var6.rl()) {
                  var5 = var15 - (int)(var4 * var8) * 3;
               }
            }
         }
      }

      return var5 >= 0 ? var5 : 0;
   }

   long pC(axv var1, INativeDataItem var2, int var3, long var4, int var6, int var7) {
      Assert.a(!(var2 instanceof auxx));
      long var8 = var2.getMemoryAddress();
      int var10 = (int)var2.getMemorySize();
      int var11 = 0;
      if (this.mv && !(var2 instanceof auw)) {
         var11 = this.pC(var2);
      }

      byte[] var12 = new byte[var10 + var11];
      int var13 = VirtualMemoryUtil.readBytesSafe(this.wS.getMemory(), var8, var12.length, var12, 0, 1);
      Assert.a(var13 == var10 + var11);
      this.pC(var1, var2);
      int var14 = 0;
      axy var15 = this.pC(var12, 0, var2, var1, new ArrayDeque());
      axy var16 = null;
      if (!(var2 instanceof ava)) {
         axv var17 = new axv();
         var16 = this.pC(var12, 0, var2, var17, new ArrayDeque());
         if (var6 > 0 && var15 instanceof axt) {
            int var18 = ((auv)var2).z().getSize();
            ((axt)var15).oT = var6;
            ((axt)var16).oT = var6;
            ((axt)var15).kS = var6 * var18;
            ((axt)var16).kS = var6 * var18;
         }
      }

      int var28 = 0;

      while (var15.pC()) {
         Assert.a(var16 == null || var16.pC());
         int var29 = var15.wS();
         long var19 = var8 + var29;
         if (var4 != -1L && Longs.compareUnsigned(var19, var4) >= 0) {
            var28++;
         }

         NativeCoordinates var21 = new NativeCoordinates(var19);
         if (Longs.compareUnsigned(var19, var8) > 0) {
            long var22 = this.pC(var19, true);
            var1.registerAnchor(var22, "a_" + var19);
         }

         int var30 = var16 == null ? var15.kS() : var16.UT();
         boolean var23 = var15.A();
         this.pC(var1, var19, var12, var29, Math.min(var30, var10 - var29) + (var23 ? var11 : 0), var2, var14 == 0 ? 0 : -1, false);
         var15.UT();
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
         if (var1.pC()) {
            List var25 = var1.kS();
            String var26 = (String)var25.get(0);
            if (var25.size() >= 2) {
               String var27 = (String)var25.get(var25.size() - 1);
               if (!var26.equals(var27)) {
                  var26 = var26 + " ... " + var27;
               }
            }

            var31 = var31 + var26;
         }

         String var32 = this.pC(var2, var19);
         if (var32 != null && var32.length() > 0) {
            if (var31.length() > 0) {
               var31 = var31 + "\n";
            }

            var31 = var31 + var32;
         }

         this.pC(var1, var19, var21, var31, true);
         var14++;
         if (var3 != -1 && var14 > var3 || var7 > 0 && var28 > 0 && var28 >= var7 * 3) {
            break;
         }
      }

      return var2.getMemoryAddressEnd() + var11;
   }

   private INativeContinuousItem kS(long var1, boolean var3) {
      if (this.mv) {
         if (var3) {
            INativeContinuousItem var4 = this.ys.getItemOver(var1);
            if (var4 != null && !(var4 instanceof auxx)) {
               return null;
            }
         }

         INativeContinuousItem var5 = this.ys.getPreviousItem(var1);
         if (var5 instanceof INativeDataItem && !(var5 instanceof auw) && this.pC((INativeDataItem)var5) > 0) {
            return var5;
         }
      }

      return null;
   }

   private int pC(INativeDataItem var1) {
      this.ys.getItemAt(var1.getMemoryAddressEnd());
      if (!(var1 instanceof avb) && var1.getMemorySize() != 1L && var1.getMemorySize() != 2L) {
         return 0;
      } else {
         INativeContinuousItem var2 = this.ys.getNextItem(var1);
         if (var2 != null && var2.getMemoryAddress() - var1.getMemoryAddressEnd() < 32L && this.A(var1.getMemoryAddressEnd(), var2.getMemoryAddress())) {
            NativeCoordinates var3 = new NativeCoordinates(var1.getMemoryAddressEnd());
            if (this.wS.getCommentManager().getComment2(var3) == null && this.ys.gp().getReferencesTo(var1.getMemoryAddressEnd()).isEmpty()) {
               int var4 = (int)(var2.getMemoryAddress() - var1.getMemoryAddressEnd());
               if (this.ED) {
                  axz.Sv var5 = (axz.Sv)this.fI.getSegmentAfter(var1.getMemoryAddressEnd() - 1L);
                  if (var5 != null && var5.pC() < var1.getMemoryAddressEnd() + var4) {
                     var4 = (int)(var5.pC() - var1.getMemoryAddressEnd());
                  }

                  var5 = (axz.Sv)this.WR.getSegmentAfter(var1.getMemoryAddressEnd() - 1L);
                  if (var5 != null && var5.pC() < var1.getMemoryAddressEnd() + var4) {
                     var4 = (int)(var5.pC() - var1.getMemoryAddressEnd());
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

   private boolean A(long var1, long var3) {
      for (long var5 = var1; var5 < var3; var5++) {
         try {
            if (this.UT.readByte(var5) != 0) {
               return false;
            }
         } catch (MemoryException var7) {
            return false;
         }
      }

      return true;
   }

   long pC(axv var1, auxx var2, int var3, long var4) {
      long var6 = var2.getMemoryAddress();
      long var8 = var2.getMemorySize();
      int var10 = (int)Math.min(var8, 1048576L);
      byte[] var11 = new byte[var10];
      int var12 = VirtualMemoryUtil.readBytesSafe(this.wS.getMemory(), var6, var10, var11, 0, 1);
      int var13 = 16;
      if (this.os > 0 && this.os < 4096 && MathUtil.isPowerOfTwo(this.os)) {
         var13 = this.os;
      }

      int var14 = 1;
      if (this.Cu == 1 || this.Cu == 2 || this.Cu == 4 || this.Cu == 8) {
         var14 = this.Cu;
      }

      String var15 = this.sY.getRawDataDeclarator(8);
      String var16 = this.sY.getDataSeparator();
      int var17 = 0;
      int var18 = Math.min(var12, var13 - (int)(var6 % var13));

      while (var17 < var12) {
         long var19 = var6 + var17;
         NativeCoordinates var21 = new NativeCoordinates(var19);
         if (Longs.compareUnsigned(var19, var6) > 0) {
            long var22 = this.pC(var19, true);
            var1.registerAnchor(var22, "a_" + var19);
         }

         this.pC(var1, var19, var11, var17, var18, null, -1, false);
         if (!this.sO) {
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
            if (this.wS.getEndianness() != null) {
               var30.order(this.wS.getEndianness().toByteOrder());
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

         this.pC(var1, var19, var21, null, true);
         var17 += var18;
         var18 = Math.min(var12 - var17, var13);
         if (var3 >= 0 && var1.getLineCount() >= var3) {
            break;
         }
      }

      return var2.getMemoryAddressEnd();
   }

   void pC(axv var1, long var2, boolean var4) {
      StringBuilder var5 = new StringBuilder();
      axz.Sv var6 = (axz.Sv)this.fI.getSegmentContaining(var2);
      axz.Sv var7 = (axz.Sv)this.WR.getSegmentContaining(var2);
      String var8;
      if (var6 == null && var7 == null) {
         var8 = "SLACK";
      } else if (var6 != null && var7 == null) {
         var8 = var6.wS;
      } else if (var6 != null && var6.wS.length() + var7.wS.length() <= this.oT && !var6.wS.equals(var7.wS)) {
         var8 = var6.wS + var7.wS;
      } else {
         var8 = var7.wS;
      }

      var5.append(var8);
      var5.append(':');
      long var9 = this.wS.getPhysicalImageDelta() + var2;
      int var11 = this.wS.getMemory().getSpaceBits();
      if (var11 <= 32) {
         var5.append(Formatter.toHexString(var9, true, 8));
      } else if (var11 <= 64) {
         if (this.hZ != null && !this.hZ.isEmpty()) {
            var5.append(Formatter.toHexString((int)(var9 >> 32), true, 8)).append(this.hZ).append(Formatter.toHexString((int)var9, true, 8));
         } else {
            var5.append(Formatter.toHexString(var9, true, 16));
         }
      } else {
         Strings.ff(var5, "%16X", var9);
      }

      ItemClassIdentifiers var12 = var6 != null ? ItemClassIdentifiers.ADDRESS : ItemClassIdentifiers.ADDRESS_SLACK;
      var1.appendAndRecord(var5.toString(), var12);
      if (var4) {
         int var13 = this.oT - var8.length();
         var1.space(var13);
         var1.space(1);
      }
   }

   void pC(axv var1, byte[] var2, int var3, int var4) {
      StringBuilder var5 = new StringBuilder();
      if (var2 == null) {
         var5.append(Strings.pad(' ', this.xC * 3));
      } else {
         var5.append(Formatter.formatBinaryLineTruncate(var2, var3, var4, this.xC));
      }

      var5.append(" ");
      var1.appendAndRecord(var5.toString(), ItemClassIdentifiers.BYTECODE);
   }

   void pC(axv var1, long var2, INativeMemoryItem var4, int var5, boolean var6) {
      if (var5 < 0) {
         var1.space(this.FE + 1);
      } else {
         String var7 = null;
         if (var4 != null) {
            var7 = var4.getName(true);
         }

         if (var7 == null) {
            var7 = this.A(var2, var5 > 0);
         }

         if (var7 == null) {
            var1.space(this.FE + 1);
         } else {
            this.pC(var1, var2, var7, true, var6);
            Collection var8 = this.wS.getCodeModel().getLabelManager().getAlternateLabels(var2);
            if (var8 != null) {
               for (String var10 : var8) {
                  var1.eol();
                  this.pC(var1, this.UW);
                  this.pC(var1, var2, var10, false, var6);
               }
            }
         }
      }

      if (var6) {
         var1.eol();
      }
   }

   private void pC(axv var1, long var2, String var4, boolean var5, boolean var6) {
      String var7 = var4;
      if (var6) {
         var7 = this.sY.getLabelPrefix() + var4 + this.sY.getLabelSuffix();
      }

      ItemClassIdentifiers var8;
      if (var5) {
         var8 = this.sY.getBestClassIdForAddress(var2);
      } else {
         var8 = ItemClassIdentifiers.LABEL_ALTERNATE;
      }

      var1.appendAndRecord(var7, var8, this.wS.UT(var2));
      if (!var6) {
         if (var7.length() < this.FE) {
            int var9 = this.FE - var7.length();
            var1.space(var9);
         }

         var1.space();
      }
   }

   void pC(axv var1, CharSequence var2, boolean var3, boolean var4) {
      this.pC(var1, var2, var3 ? 2 : 0, var4);
   }

   void pC(axv var1, CharSequence var2, int var3, boolean var4) {
      if (var3 != 0) {
         this.pC(var1, switch (var3) {
            case 1 -> this.UW;
            case 2 -> this.PR;
            case 3 -> this.cX;
            default -> "";
         });
      }

      String var6 = this.sY.getInlineCommentString() + " " + var2;
      var1.appendComment(var6, false);
      if (var4) {
         var1.eol();
      }
   }

   void pC(axv var1, String var2, String var3) {
      if (var3 != null && this.pC(var1, var3) <= 0) {
         var1.space();
      }

      int var4 = 0;
      String[] var5 = Strings.splitLines(var2);

      for (String var9 : var5) {
         if (var4 >= 1) {
            var1.eol();
            if (var3 != null) {
               this.pC(var1, var3);
            }
         }

         String var10 = this.sY.getInlineCommentString() + " " + var9;
         var1.appendComment(var10);
         var4++;
      }
   }

   int pC(axv var1, String var2) {
      int var3 = var2.length() - var1.getCurrentLineLength();
      if (var3 > 0) {
         var1.space(var3);
      }

      return var3;
   }

   public static void pC(IPropertyDefinitionManager var0) {
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

   private void pC(boolean var1) {
      IPropertyManager var2 = this.wS.getPropertyManager();
      if (this.NS != null && this.NS.getShowBytesCount() != null) {
         this.xC = this.NS.getShowBytesCount();
      } else {
         this.xC = var2.getInteger("ShowBytesCount", 0);
      }

      if (this.NS != null && this.NS.getShowAddresses() != null) {
         this.vP = this.NS.getShowAddresses();
      } else {
         this.vP = var2.getBoolean("ShowAddresses", true);
      }

      if (this.NS != null && this.NS.getShowSegmentHeaders() != null) {
         this.ED = this.NS.getShowSegmentHeaders();
      } else {
         this.ED = var2.getBoolean("ShowSegmentHeaders", true);
      }

      if (this.NS != null && this.NS.getSegmentHeadersSeparator() != null) {
         this.Sc = this.NS.getSegmentHeadersSeparator();
      } else {
         this.Sc = var2.getString("SegmentHeadersSeparator", ".");
      }

      if (Strings.isBlank(this.Sc)) {
         this.Sc = ".";
      }

      if (this.NS != null && this.NS.getSegmentHeadersSeparatorLength() != null) {
         this.ah = this.NS.getSegmentHeadersSeparatorLength();
      } else {
         this.ah = var2.getInteger("SegmentHeadersSeparatorLength", 80);
      }

      if (this.NS != null && this.NS.getSegmentHeadersSeparatorLengthABL() != null) {
         this.eP = this.NS.getSegmentHeadersSeparatorLengthABL();
      } else {
         this.eP = var2.getBoolean("SegmentHeadersSeparatorLengthABL", true);
      }

      if (this.NS != null && this.NS.getSegmentHeadersIndentABL() != null) {
         this.UO = this.NS.getSegmentHeadersIndentABL();
      } else {
         this.UO = var2.getBoolean("SegmentHeadersIndentABL", false);
      }

      if (this.NS != null && this.NS.getShowRoutineHeaders() != null) {
         this.Ab = this.NS.getShowRoutineHeaders();
      } else {
         this.Ab = var2.getBoolean("ShowRoutineHeaders", true);
      }

      if (this.NS != null && this.NS.getRoutineHeadersSeparator() != null) {
         this.rl = this.NS.getRoutineHeadersSeparator();
      } else {
         this.rl = var2.getString("RoutineHeadersSeparator", "=");
      }

      if (Strings.isBlank(this.rl)) {
         this.rl = "=";
      }

      if (this.NS != null && this.NS.getRoutineEndSeparator() != null) {
         this.z = this.NS.getRoutineEndSeparator();
      } else {
         this.z = var2.getString("RoutineEndSeparator", "-");
      }

      if (Strings.isBlank(this.z)) {
         this.z = "-";
      }

      if (this.NS != null && this.NS.getRoutineSeparatorLength() != null) {
         this.Ek = this.NS.getRoutineSeparatorLength();
      } else {
         this.Ek = var2.getInteger("RoutineSeparatorLength", 80);
      }

      if (this.NS != null && this.NS.getRoutineHeadersSeparatorLengthABL() != null) {
         this.hK = this.NS.getRoutineHeadersSeparatorLengthABL();
      } else {
         this.hK = var2.getBoolean("RoutineHeadersSeparatorLengthABL", false);
      }

      if (this.NS != null && this.NS.getRoutineHeadersIndentABL() != null) {
         this.Er = this.NS.getRoutineHeadersIndentABL();
      } else {
         this.Er = var2.getBoolean("RoutineHeadersIndentABL", true);
      }

      if (this.NS != null && this.NS.getShowSpaceBetweenBlocks() != null) {
         this.LM = this.NS.getShowSpaceBetweenBlocks();
      } else {
         this.LM = var2.getBoolean("ShowSpaceBetweenBlocks", false);
      }

      if (this.NS != null && this.NS.getHide0Padding() != null) {
         this.mv = this.NS.getHide0Padding();
      } else {
         this.mv = var2.getBoolean("Hide0Padding", true);
      }

      if (this.NS != null && this.NS.getLabelAreaLength() != null) {
         this.FE = this.NS.getLabelAreaLength();
      } else {
         this.FE = var2.getInteger("LabelAreaLength", 16);
      }

      if (this.NS != null && this.NS.getInstructionAreaLength() != null) {
         this.Aj = this.NS.getInstructionAreaLength();
      } else {
         this.Aj = var2.getInteger("InstructionAreaLength", 40);
      }

      if (this.NS != null && this.NS.getBlockXrefsCount() != null) {
         this.EX = this.NS.getBlockXrefsCount();
      } else {
         this.EX = var2.getInteger("BlockXrefsCount", 50);
      }

      if (this.NS != null && this.NS.getGapPreferRawFormatting() != null) {
         this.sO = this.NS.getGapPreferRawFormatting();
      } else {
         this.sO = var2.getBoolean("GapPreferRawFormatting", false);
      }

      if (this.NS != null && this.NS.getGapRawBytesPerLine() != null) {
         this.os = this.NS.getGapRawBytesPerLine();
      } else {
         this.os = var2.getInteger("GapRawBytesPerLine", 16);
      }

      if (this.NS != null && this.NS.getGapRawIntegerSize() != null) {
         this.Cu = this.NS.getGapRawIntegerSize();
      } else {
         this.Cu = var2.getInteger("GapRawIntegerSize", 1);
      }

      if (this.NS != null && this.NS.getGapRawIntegerSize() != null) {
         this.hZ = this.NS.getCharBreak64BitAddresses();
      } else {
         this.hZ = var2.getString("CharBreak64BitAddresses", "'");
      }

      ICodeObjectUnit var3 = this.wS.getCodeObjectContainer();
      if (var3 != null) {
         this.pC(var3.getValidSegments(), true, this.fI);
         this.pC(var3.getValidSections(), false, this.WR);
      }

      this.UW = Strings.generate(' ', this.pC(true, true, false, false));
      this.PR = Strings.generate(' ', this.pC(true, true, true, false));
      this.cX = Strings.generate(' ', this.pC(true, true, true, true));
      if (var1) {
         this.notifyListeners(new JebEvent(J.UnitChange));
      }
   }

   @Override
   public NativeDisassemblyProperties getPropertyOverrides() {
      return this.NS;
   }

   @Override
   public void setPropertyOverrides(NativeDisassemblyProperties var1) {
      this.NS = var1;
      this.pC(true);
   }

   private void pC(Iterable var1, boolean var2, SegmentMap var3) {
      for (ISegmentInformation var5 : var1) {
         if ((var2 || var5.getOffsetInMemory() != 0L) && var5.getSizeInMemory() != 0L) {
            String var6 = var5.getName();
            if (var6 != null && var6.length() > 9) {
               var6 = var6.substring(0, 9);
            } else {
               var6 = Strings.safe(var6);
            }

            axz.Sv var7;
            try {
               var7 = new axz.Sv(var5, var6);
            } catch (RuntimeException var8) {
               continue;
            }

            if (var3.isEmptyRange(var7.pC(), var7.A())) {
               var3.add(var7);
               if (var6.length() > this.oT) {
                  this.oT = var6.length();
               }
            }
         }
      }
   }

   private int pC(boolean var1, boolean var2, boolean var3, boolean var4) {
      int var10 = 0;
      if (var1 && this.vP) {
         var10 += this.oT + 1;
         int var6 = this.wS.getMemory().getSpaceBits();
         if (var6 <= 32) {
            var10 += 8;
         } else if (var6 <= 64) {
            var10 += 17;
         } else {
            var10 += 16;
         }

         var10++;
      }

      if (var2 && this.xC > 0) {
         var10 += this.xC * 3;
         var10++;
      }

      if (var3 && this.FE > 0) {
         var10 += this.FE;
         var10++;
      }

      if (var4 && this.Aj > 0) {
         var10 += this.Aj;
      }

      return var10;
   }

   long pC(axv var1, long var2) {
      if (!this.ED) {
         return -1L;
      } else {
         axz.Sv var4 = (axz.Sv)this.fI.get(var2);
         axz.Sv var5 = (axz.Sv)this.WR.get(var2);
         if (var4 == null && var5 == null) {
            return -1L;
         } else {
            var1.eol();
            String var6 = Strings.generate(this.Sc, ((this.eP ? this.PR.length() - 2 : 0) + this.ah) / this.Sc.length());
            this.pC(var1, var6, this.UO, true);
            this.pC(var1, "", this.UO, true);
            long var7 = 1L;
            if (var4 != null) {
               this.pC(var1, var4.kS, true, var5 == null ? null : var5.kS);
               var7 = var4.A;
               String var9 = this.sY.generateExtraSegmentHeader(var4.kS);
               if (var9 != null) {
                  this.pC(var1, var9, null);
               }
            }

            if (var5 != null) {
               this.pC(var1, var5.kS, false, var4 == null ? null : var4.kS);
               var7 = var7 == -1L ? var5.A : Math.min(var7, var5.A);
               String var10 = this.sY.generateExtraSectionHeader(var5.kS);
               if (var10 != null) {
                  this.pC(var1, var10, null);
               }
            }

            this.pC(var1, "", this.UO, true);
            this.pC(var1, var6, this.UO, true);
            var1.eol();
            return var7;
         }
      }
   }

   private void pC(axv var1, ISegmentInformation var2, boolean var3, ISegmentInformation var4) {
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
      String var13 = pC(var2.getFlags());
      if (!var13.isEmpty()) {
         var5.append("Permissions:").append(var13);
      }

      this.pC(var1, var5.toString(), this.UO, true);
   }

   private static String pC(int var0) {
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

   void pC(axv var1) {
      String var2 = this.sY.generateHeader();
      if (var2 != null) {
         this.pC(var1, var2, null);
         var1.eol();
      }

      if (!this.wS.isAnalysisCompleted()) {
         var1.eol();
         var2 = S.L("An analysis pass is ongoing, please wait...");
         this.pC(var1, var2, false, true);
      } else if (!this.wS.isInitialAnalysisDone()) {
         var1.eol();
         var2 = S.L(
            "The initial analysis has not been performed. How to start one:\n- GUI: in the 'Native Code' menu, via the command 'Start or resume an Analysis'\n- API: via the INativeCodeUnit.performInitialAnalysis() method"
         );
         this.pC(var1, var2, null);
         var1.eol();
      }
   }

   void A(axv var1, String var2) {
      var1.appendAndRecord(var2, ItemClassIdentifiers.KEYWORD);
   }

   String A(long var1, boolean var3) {
      return this.wS.pC(var1, var3 ? AutoLabelPolicy.ON : AutoLabelPolicy.OFF);
   }

   private void A(axv var1, long var2) {
      this.pC(var1, var2, null, 0, 0, null, -1, true);
   }

   private void A(axv var1, long var2, boolean var4) {
      if (this.vP) {
         this.pC(var1, var2, var4);
      }
   }

   public void pC(axv var1, long var2, byte[] var4, int var5, int var6, INativeMemoryItem var7, int var8, boolean var9) {
      if (var1.getCurrentLineLength() != 0) {
         throw new RuntimeException(Strings.ff("Line not empty: \"%s\"", Formatter.escapeString(var1.getCurrentLine().getText())));
      } else {
         boolean var10 = var4 == null && var8 < 0 && var9;
         this.A(var1, var2, !var10);
         if (var10) {
            var1.eol();
         } else {
            if (this.xC > 0) {
               this.pC(var1, var4, var5, var6);
            }

            if (var7 != null) {
               var2 = var7.getMemoryAddress();
            }

            this.pC(var1, var2, var7, var8, var9);
         }
      }
   }

   boolean pC(axv var1, ICodeCoordinates var2) {
      Comment var3 = this.wS.getCommentManager().getComment2(var2);
      if (var3 == null) {
         return false;
      } else {
         CommentGenerator var4 = new CommentGenerator(var1, this.sY.getInlineCommentString());
         var4.setMargin(this.PR.length());
         return var4.genPre(var2, var3, null);
      }
   }

   boolean A(axv var1, ICodeCoordinates var2) {
      String var3 = this.wS.getCommentManager().getPrimary2(var2, 0);
      if (var3 == null) {
         return false;
      } else {
         this.pC(var1, var3, this.cX);
         return true;
      }
   }

   public void pC(axv var1, long var2, ICodeCoordinates var4, String var5, boolean var6) {
      Comment var7 = this.wS.getCommentManager().getComment2(var4);
      CommentGenerator var8 = new CommentGenerator(var1, this.sY.getInlineCommentString());
      var8.setMargin(this.cX.length());
      var8.genInline(var4, var7, 0, 0, var5);
      if (var6) {
         var1.eol();
      }
   }

   private String pC(INativeDataItem var1, long var2) {
      StringBuilder var4 = new StringBuilder();
      long var5 = var1.getMemoryAddress();
      Object var7 = null;
      if (var5 == var2) {
         var7 = this.ys.gp().getReferencesTo(var5);
         TreeSet var8 = new TreeSet((Collection)var7);
         var7 = var8;
         DataHints var9 = var1.getHints(false);
         if (var9 != null && var9.getReferenceHint() != 0) {
            for (long var10 = var1.getMemoryAddress() + 1L; var10 < var1.getMemoryAddressEnd(); var10++) {
               for (xy var14 : this.ys.gp().getReferencesTo(var10)) {
                  xy var15 = (xy)var8.floor(var14);
                  if (var15 == null || !var15.getFrom().equals(var14.getFrom())) {
                     var8.add(var14);
                  }
               }
            }
         }
      } else if (var1 instanceof auw) {
         var7 = this.ys.gp().getReferencesTo(var2);
      } else if (!(var1 instanceof auv) && var2 < var5 + 16L) {
         var7 = this.ys.gp().getReferencesTo(var5);
      }

      if (var7 != null && !var7.isEmpty()) {
         this.pC((Set)var7, var4);
      }

      return var4.toString();
   }

   private void pC(Set var1, StringBuilder var2) {
      StringBuilder var3 = new StringBuilder();
      int var4 = 0;
      byte var5 = 0;

      for (xy var7 : var1) {
         if (var7.getFrom().isInternalAddress()) {
            if (this.EX >= 0 && var4 >= this.EX) {
               break;
            }

            long var8 = var7.getFrom().getInternalAddress();
            if (var4 >= 1) {
               var3.append(" / ");
            }

            String var10 = this.wS.getSymbolicStringAddress(var8, 2);
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

   private String pC(aus var1) {
      StringBuilder var2 = new StringBuilder();
      long var3 = var1.getMemoryAddress();
      long var5 = var3 + var1.getMemorySize();
      Me var7 = this.ys.WR(var3);
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
               if (var13 instanceof aab) {
                  var2.append("[");
                  int var16 = -1;
                  int var17 = -1;

                  for (int var19 : ((aab)var13).pC()) {
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

               var2.append(this.A(var14, true));
               var8++;
            }

            if (var2.length() - var11 > var9) {
               var2.append("\n");
               var11 = var2.length();
            }
         }
      }

      CharSequence var20 = this.sY.generateExtraComment(var3, var1.getInstruction());
      if (var20 != null) {
         if (var2.length() != 0) {
            var2.append("\n");
         }

         var2.append(var20);
      }

      kf var21 = this.ys.NS(var3);
      if (!var21.isEmpty()) {
         this.pC(var2, var21, false, var5);
         this.pC(var2, var21, true, var5);
      }

      Set var22 = this.ys.gp().getReferencesTo(var3);
      if (!var22.isEmpty()) {
         StringBuilder var23 = new StringBuilder();
         int var25 = 0;
         int var26 = 0;

         for (xy var15 : var22) {
            if (var15.getFrom().isInternalAddress()) {
               if (this.EX >= 0 && var25 >= this.EX) {
                  break;
               }

               long var29 = var15.getFrom().getInternalAddress();
               INativeContinuousItem var30 = this.ys.getItemAt(var29);
               if (var30 != null && var15.getType() == ReferenceType.COND_BRANCH && (Long)var30.getEnd() == var3) {
                  var26++;
               } else if (var30 != null && (Long)var30.getBegin() == var3) {
                  var26++;
               } else {
                  if (var25 >= 1) {
                     var23.append(" / ");
                  }

                  String var31 = this.wS.getSymbolicStringAddress(var29, 2);
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

      int var24 = var1.UT();
      if (var24 == 2) {
         var2.append("\nswitch dispatcher");
      }

      return var2.toString();
   }

   private void pC(StringBuilder var1, kf var2, boolean var3, long var4) {
      int var6 = 0;

      for (Entry var8 : var2.getAll(var3).entrySet()) {
         long var9 = (Long)var8.getKey();
         String var11 = this.sY.getRegisterName(var9);
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

               var1.append(this.A(var15, true));
               INativeContinuousItem var17 = this.ys.getItemAt(var15);
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

   public axy pC(byte[] var1, int var2, INativeDataItem var3, axv var4, Deque var5) {
      if (var3 instanceof auv) {
         return new axt(var1, var2, (auv)var3, var4, this.sY, this, var5);
      } else if (var3 instanceof auw) {
         return new axw(var1, var2, (auw)var3, var4, this.sY, this, var5);
      } else {
         return (axy)(!(var3 instanceof ava) && !(var3 instanceof auz) ? new ayd(var4) : new ayc(var1, var2, var3, var4, this.sY, this, var5));
      }
   }

   @Override
   public INativeCodeUnit getUnit() {
      return this.wS;
   }

   @Override
   public boolean hasBinaryRepresentation() {
      return true;
   }

   @Override
   public IBinaryRepresentation getBinaryRepresentation() {
      return new axz.Av();
   }

   static {
      Assert.a("SLACK".length() <= 9);
   }

   private class Av implements IBinaryRepresentation {
      Av() {
      }

      @Override
      public long getBaseOffsetHint() {
         return 0L;
      }

      @Override
      public int read(long var1, int var3, byte[] var4, int var5) {
         return VirtualMemoryUtil.readBytesSafe(axz.this.UT, var1, var3, var4, var5, true);
      }

      @Override
      public long find(long var1, long var3, byte[] var5, byte[] var6) {
         return VirtualMemoryUtil.findBytes(axz.this.UT, true, var1, var3, var5, var6);
      }

      @Override
      public long convertCoordinatesToOffset(ICoordinates var1) {
         String var2 = axz.this.coordinatesToAddress(var1);
         return var2 == null ? -1L : axz.this.wS.getCanonicalMemoryAddress(var2);
      }

      @Override
      public ICoordinates convertOffsetToCoordinates(long var1) {
         String var3 = axz.this.wS.getSymbolicStringAddress(var1);
         return var3 == null ? null : axz.this.addressToCoordinates(var3);
      }
   }

   @SerDisabled
   private class Sv implements ISegment {
      Long pC;
      Long A;
      ISegmentInformation kS;
      String wS;

      Sv(ISegmentInformation var2, String var3) {
         if (var2 == null) {
            throw new NullPointerException();
         } else {
            this.kS = var2;
            this.pC = axz.this.wS.getVirtualImageBase() + var2.getOffsetInMemory();
            this.A = this.pC + var2.getSizeInMemory();
            if (!axz.this.wS.pC(this.pC, this.A - 1L)) {
               throw new JebRuntimeException("Illegal segment");
            } else if (var3 == null) {
               throw new NullPointerException();
            } else {
               this.wS = var3;
            }
         }
      }

      public Long pC() {
         return this.pC;
      }

      public Long A() {
         return this.A;
      }
   }
}
