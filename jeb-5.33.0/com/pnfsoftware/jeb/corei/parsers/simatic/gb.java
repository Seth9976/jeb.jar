package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.simatic.IS7Block;
import com.pnfsoftware.jeb.core.units.code.simatic.IS7Unit;
import com.pnfsoftware.jeb.core.units.code.simatic.S7;
import com.pnfsoftware.jeb.core.units.codeobject.AbstractCodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ILinkInfoProvider;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.LoaderInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolType;
import com.pnfsoftware.jeb.core.units.impl.ContainerUnit;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Throwables;
import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

@Ser
public class gb extends AbstractCodeObjectUnit implements IS7Unit {
   private static final ILogger UT = GlobalLog.getLogger(gb.class);
   @SerId(1)
   byte[] pC;
   @SerId(2)
   List A = new ArrayList();
   @SerId(3)
   Map kS = new HashMap();
   @SerId(4)
   Map wS = new HashMap();
   private static final Map E = new HashMap();
   @SerTransient
   private Map sY = new HashMap();

   public gb(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var2, "simatic_s7", var1, var3, var4, var5);
   }

   @Override
   public List getBlocks() {
      return Collections.unmodifiableList(this.A);
   }

   @Override
   public IS7Block findBlock(S7.BlockType var1, int var2) {
      for (IS7Block var4 : this.A) {
         if (var4.getType() == var1 && var4.getNumber() == var2) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public String getBlockEntryName(IS7Block var1) {
      return (String)this.kS.get(var1.getName());
   }

   public boolean pC() {
      return AbstractUnitIdentifier.checkBytes(this.getInput(), 0, 80, 75, 3, 4);
   }

   @Override
   protected boolean processInternal() {
      if (this.pC()) {
         try {
            ZipFile var1 = new ZipFile(this.getInput().getChannel());

            try {
               Enumeration var2 = var1.getEntries();

               while (var2.hasMoreElements()) {
                  ZipArchiveEntry var3 = (ZipArchiveEntry)var2.nextElement();
                  if (!var3.isDirectory() && var1.canReadEntryData(var3)) {
                     try {
                        byte[] var4 = IO.readInputStream(var1.getInputStream(var3));
                        IS7Block var5 = this.pC(var4);
                        if (var5 != null) {
                           this.kS.put(var5.getName(), var3.getName());
                        }
                     } catch (IOException var16) {
                        UT.catching(var16);
                     }
                  }
               }
            } catch (Throwable var19) {
               try {
                  var1.close();
               } catch (Throwable var11) {
                  var19.addSuppressed(var11);
               }

               throw var19;
            }

            var1.close();
         } catch (IOException var20) {
            UT.catchingSilent(var20);
            return false;
         }
      } else {
         try (InputStream var21 = this.getInput().getStream()) {
            byte[] var23 = IO.readInputStream(var21);
            this.pC(var23);
         } catch (IOException var18) {
            UT.catchingSilent(var18);
            return false;
         }
      }

      this.A();
      if (this.A.isEmpty()) {
         return false;
      } else {
         this.kS();
         LoaderInformation var22 = new LoaderInformation.Builder()
            .setFlags(0)
            .setVersion("1")
            .setTargetProcessor(MC7Plugin.pC)
            .setWordSize(32)
            .setEndianness(Endianness.BIG_ENDIAN)
            .setImageBase(1048576L)
            .setImageSize(this.pC.length)
            .setEntryPoint(0L)
            .build();
         this.setLoaderInformation(var22);
         if (this.getPropertyManager().getBoolean("DisassembleCode")) {
            try {
               String var24 = "simatic_mc7";
               IUnit var26 = this.getUnitProcessor().process(var24 + "-image", this.getInput(), this, var24, true);
               if (var26 != null) {
                  this.addChild(var26);
               }
            } catch (Exception var15) {
               UT.catching(var15);
            }
         }

         if (this.getPropertyManager().getBoolean("GenerateInterfaceDescriptionUnits")) {
            ContainerUnit var25 = new ContainerUnit("Interfaces", this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
            var25.process();
            this.addChild(var25);

            for (IS7Block var28 : this.A) {
               StringBuilder var29 = new StringBuilder();

               try {
                  GK var6 = this.pC(var28);
                  Strings.ff(var29, "Interface and initial values for block %s:\n", var28.getName());
                  var29.append(var6.pC());
                  var29.append('\n');

                  try {
                     if (var28.getType().isDataBlock()) {
                        byte[] var7 = new Tb().pC(var6);
                        byte[] var8 = var28.getPayloadBytes();
                        if (var7 != null && !Arrays.equals(var7, var8)) {
                           var29.append("\nIMPORTANT! The current values stored in this data block differ from the initial values.\n");
                           boolean var9 = this.getPropertyManager().getBoolean("MapActualBytesForDataBlocks");
                           Strings.ff(
                              var29, "The %s values will be used for mapping in the code unit ('simatic_mc7 image' node).\n", var9 ? "current" : "initial"
                           );
                        }

                        Strings.ff(var29, "\nCurrent values for data block %s:\n", var28.getName());
                        var29.append(var6.A(var8));
                        var29.append('\n');
                     }
                  } catch (Exception var13) {
                     var29.append("An error occurred when processing the current values of a data block:\n");
                     var29.append(Throwables.formatStacktrace(var13));
                  }
               } catch (Exception var14) {
                  var29.append("An error occurred when processing the block interface:\n");
                  var29.append(Throwables.formatStacktrace(var14));
               }

               IUnit var30 = this.getUnitProcessor()
                  .process(var28.getName() + " interface", new BytesInput(Strings.encodeUTF8(var29.toString())), var25, "text");
               var25.addChild(var30);
            }
         }

         try {
            JebCoreService.notifyTelemetryToClient("s7_unit_processed", "block_count", this.A.size() + "");
         } catch (Exception var12) {
         }

         return true;
      }
   }

   private IS7Block pC(byte[] var1) {
      if (!uX.pC(var1)) {
         return null;
      } else {
         IS7Block var2;
         try {
            var2 = uX.A(var1);
         } catch (Exception var3) {
            return null;
         }

         if (var2.getType() == S7.BlockType.UNKNOWN || var2.getSourceLanguage() == S7.LangType.UNKNOWN) {
            this.pC(null, var2, "Unsupported block type or language (type: %d, lang: %d)", var2.getTypeId(), var2.getSourceLanguageId());
         }

         this.A.add(var2);
         return var2;
      }
   }

   private int A() {
      int var1 = 0;
      HashSet var2 = new HashSet();
      Iterator var3 = this.A.iterator();

      while (var3.hasNext()) {
         IS7Block var4 = (IS7Block)var3.next();
         S7.BlockType var5 = S7.BlockType.fromId(var4.getTypeId());
         if (var5 == null) {
            UT.warn(S.L("Removing block of unknown type id: %d"), var4.getTypeId());
            var3.remove();
            var1++;
         } else {
            int var6 = var4.getNumber();
            if (var6 == 0) {
               UT.warn(S.L("A block has id 0!"));
            } else {
               String var7 = var4.getName();
               if (!var2.add(var7)) {
                  UT.warn(S.L("Removing duplicate block: %s"), var7);
                  var3.remove();
                  var1++;
               }
            }
         }
      }

      this.A.sort(new Kr(this));
      return var1;
   }

   private int pC(int var1, BytePipe var2) {
      int var3 = var2.limit();
      int var4 = var3 % var1;
      if (var4 == 0) {
         return 0;
      } else {
         int var5 = var1 - var4;
         var2.append(new byte[var5]);
         return var5;
      }
   }

   private void kS() {
      BytePipe var1 = new BytePipe();
      if (this.getPropertyManager().getBoolean("MapRawBlocksAtZero")) {
         for (IS7Block var3 : this.A) {
            int var4 = var1.limit();
            int var5 = var3.getBlockSize();
            this.addSegment(new SegmentInformation(this.pC(var3.getType(), var3.getNumber()), var4, var5, var4, var5, 2));
            var1.append(var3.getBlockBytes());
            this.pC(16, var1);
         }
      } else {
         int var11 = var1.limit();
         byte var13 = 16;
         this.addSegment(new SegmentInformation(".slack", var11, var13, var11, var13, 2));
         var1.append(new byte[var13]);
         this.pC(16, var1);
      }

      for (IS7Block var14 : this.A) {
         if (var14.getType().isLogicBlock()) {
            int var16 = var1.limit();
            byte[] var19 = var14.getPayloadBytes();
            boolean var6 = var19.length >= 2 && var19[var19.length - 2] == 101 && (var19[var19.length - 1] & 254) == 0;
            if (!var6) {
               var19 = Arrays.copyOf(var19, var19.length + 2);
               var19[var19.length - 2] = 101;
               var19[var19.length - 1] = 1;
               this.pC(null, var14, "Code does not end with BE/BEU");
            }

            int var7 = var19.length;
            this.addSegment(new SegmentInformation(this.A(var14.getType(), var14.getNumber()), var16, var7, var16, var7, 6));
            var1.append(var19);
            String var8 = "func_" + var14.getName();
            if (!Strings.isBlank(var14.getMetadataBlockName())) {
               var8 = var8 + "_" + var14.getMetadataBlockName();
            }

            this.addSymbol(new SymbolInformation(SymbolType.FUNCTION, 0, 0L, var8, 0L, var16, 0L));
            this.wS.put(1048576L + var16, var14);
         }
      }

      this.pC(65536, var1);
      boolean var15 = this.getPropertyManager().getBoolean("MapActualBytesForDataBlocks");

      for (IS7Block var20 : this.A) {
         if (var20.getType().isDataBlock()) {
            byte[] var26;
            if (var15) {
               var26 = var20.getPayloadBytes();
            } else {
               try {
                  GK var32 = this.pC(var20);
                  var26 = new Tb().pC(var32);
               } catch (Exception var10) {
                  var26 = new byte[0];
               }
            }

            byte[] var33 = Arrays.copyOf(var26, 65536);
            int var34 = var1.limit();
            int var9 = 65536;
            this.addSegment(new SegmentInformation(this.kS(var20.getType(), var20.getNumber()), var34, var9, var34, var9, 3));
            var1.append(var33);
            this.wS.put(1048576L + var34, var20);
         }
      }

      byte[] var18 = new byte[65536];
      int var21 = var18.length;
      int var27 = var1.limit();
      this.addSegment(new SegmentInformation(".globals", var27, var21, var27, var21, 3));
      var1.append(var18, 0, var21);
      var21 = var18.length;
      var27 = var1.limit();
      this.addSegment(new SegmentInformation(".inputs", var27, var21, var27, var21, 3));
      var1.append(var18, 0, var21);
      var21 = var18.length;
      var27 = var1.limit();
      this.addSegment(new SegmentInformation(".outputs", var27, var21, var27, var21, 3));
      var1.append(var18, 0, var21);
      short var24 = 512;
      var27 = var1.limit();
      this.addSegment(new SegmentInformation(".counters", var27, var24, var27, var24, 3));
      var1.append(var18, 0, var24);
      var24 = 512;
      var27 = var1.limit();
      this.addSegment(new SegmentInformation(".timers", var27, var24, var27, var24, 3));
      var1.append(var18, 0, var24);
      this.pC = var1.getAll();
   }

   String pC(S7.BlockType var1, int var2) {
      return ".blk_" + p.pC(var1, var2);
   }

   @Override
   public long getAddressOfRawBytes(IS7Block var1) {
      return this.pC(this.pC(var1.getType(), var1.getNumber()));
   }

   @Override
   public IS7Block getBlockAt(long var1) {
      return (IS7Block)this.wS.get(var1);
   }

   @Override
   public IS7Block getBlockContaining(long var1) {
      var1 -= 1048576L;
      if (var1 >= 0L) {
         for (ISegmentInformation var4 : this.getSegments()) {
            if (Strings.startsWith(var4.getName(), ".code_", ".data_")) {
               long var5 = var4.getOffsetInMemory();
               if (var1 >= var5 && var1 < var5 + var4.getSizeInMemory()) {
                  return this.getBlockAt(var5);
               }
            }
         }
      }

      return null;
   }

   String A(S7.BlockType var1, int var2) {
      Assert.a(var1.isLogicBlock());
      return ".code_" + p.pC(var1, var2);
   }

   @Override
   public long getAddressOfCode(S7.BlockType var1, int var2) {
      return this.pC(this.A(var1, var2));
   }

   String kS(S7.BlockType var1, int var2) {
      Assert.a(var1.isDataBlock());
      return ".data_" + p.pC(var1, var2);
   }

   @Override
   public long getAddressOfData(S7.BlockType var1, int var2) {
      return this.pC(this.kS(var1, var2));
   }

   @Override
   public long getAddressOfGlobals() {
      return this.pC(".globals");
   }

   @Override
   public long getAddressOfDigitalInputs() {
      return this.pC(".inputs");
   }

   @Override
   public long getAddressOfDigitalOutputs() {
      return this.pC(".outputs");
   }

   @Override
   public long getAddressOfCounters() {
      return this.pC(".counters");
   }

   @Override
   public long getAddressOfTimers() {
      return this.pC(".timers");
   }

   private long pC(String var1) {
      for (ISegmentInformation var3 : this.getSegments()) {
         if (var3.getName().equals(var1)) {
            return 1048576L + var3.getOffsetInMemory();
         }
      }

      return -1L;
   }

   @Override
   protected IInput getMappableInput() {
      return new BytesInput(this.pC);
   }

   @Override
   protected boolean shouldAllocateFullImage() {
      return false;
   }

   @Override
   protected boolean applyRelocations(IVirtualMemory var1, long var2, ILinkInfoProvider var4) {
      return false;
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      var1.append('\n');
      Strings.ff(var1, "Mapping of %d S7 blocks:\n", this.A.size());

      for (IS7Block var3 : this.A) {
         Strings.ff(var1, "- %s\n", var3);
      }

      var1.append('\n');
      return var1.toString();
   }

   @Override
   public byte[] getIconData() {
      try {
         byte[] var2;
         try (InputStream var1 = S7Identifier.class.getResourceAsStream("assets/s7_block.png")) {
            var2 = IO.readInputStream(var1);
         }

         return var2;
      } catch (IOException var6) {
         return null;
      }
   }

   public GK pC(IS7Block var1) {
      String var2 = var1.getName();
      GK var3 = (GK)this.sY.get(var2);
      if (var3 == null) {
         synchronized (this) {
            var3 = (GK)this.sY.get(var2);
            if (var3 == null) {
               try {
                  var3 = GK.pC(var1.getInterfaceBytes());
               } catch (Exception var7) {
                  this.pC(var7, var1, "Failed parsing interface");
               }

               this.sY.put(var2, var3);
            }
         }
      }

      return var3;
   }

   private void pC(Throwable var1, IS7Block var2, String var3, Object... var4) {
      String var5 = Strings.ff(var3, var4);
      if (var2 != null) {
         var5 = var2.getName() + ": " + var5;
      }

      RuntimeException var6;
      if (var1 == null) {
         var6 = new RuntimeException(var5);
      } else {
         var6 = new RuntimeException(var5, var1);
      }

      JebCoreService.notifySilentExceptionToClient(var6, this);
   }

   static {
      E.put(S7.BlockType.OB, 0);
      E.put(S7.BlockType.FB, 1);
      E.put(S7.BlockType.FC, 2);
      E.put(S7.BlockType.SFB, 3);
      E.put(S7.BlockType.SFC, 4);
      E.put(S7.BlockType.DI, 5);
      E.put(S7.BlockType.DB, 6);
      E.put(S7.BlockType.SDB, 7);
   }
}
