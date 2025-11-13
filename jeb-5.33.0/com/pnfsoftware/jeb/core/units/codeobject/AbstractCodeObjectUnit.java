package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.awp;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Ser
public abstract class AbstractCodeObjectUnit extends AbstractBinaryUnit implements ICodeObjectUnit {
   private static final ILogger logger = GlobalLog.getLogger(AbstractCodeObjectUnit.class);
   @SerId(1)
   private ILoaderInformation ldInfo = new LoaderInformation();
   @SerId(2)
   private List segInfo = new ArrayList();
   @SerId(3)
   private List secInfo = new ArrayList();
   @SerId(4)
   private Set symInfo = new LinkedHashSet();
   @SerTransient
   private IVirtualMemory rawmem;

   public AbstractCodeObjectUnit(IInput var1, String var2, String var3, IUnitProcessor var4, IUnitCreator var5, IPropertyDefinitionManager var6) {
      super(null, var1, var2, var3, var4, var5, var6);
   }

   @Override
   public ILoaderInformation getLoaderInformation() {
      return this.ldInfo;
   }

   protected void setLoaderInformation(ILoaderInformation var1) {
      this.ldInfo = var1;
   }

   public List getSegments() {
      return this.segInfo;
   }

   public List getValidSegments() {
      return this.getSegments(0, 536870912);
   }

   public List getSegments(int var1, int var2) {
      ArrayList var3 = new ArrayList();

      for (ISegmentInformation var5 : this.segInfo) {
         if ((var5.getFlags() & var1) == var1 && (var5.getFlags() & var2) == 0) {
            var3.add(var5);
         }
      }

      return var3;
   }

   @Override
   public ISegmentInformation getSegment(int var1) {
      return (ISegmentInformation)this.segInfo.get(var1);
   }

   @Override
   public int getSegmentCount() {
      return this.segInfo.size();
   }

   protected void addSegment(ISegmentInformation var1) {
      this.segInfo.add(var1);
   }

   protected void insertSegment(int var1, ISegmentInformation var2) {
      this.segInfo.add(var1, var2);
   }

   protected void addAllSegments(Collection var1) {
      this.segInfo.addAll(var1);
   }

   protected void removeSegment(int var1) {
      this.segInfo.remove(var1);
   }

   public List getSections() {
      return this.secInfo;
   }

   public List getValidSections() {
      return this.getSections(0, 536870912);
   }

   public List getSections(int var1, int var2) {
      ArrayList var3 = new ArrayList();

      for (ISegmentInformation var5 : this.secInfo) {
         if ((var5.getFlags() & var1) == var1 && (var5.getFlags() & var2) == 0) {
            var3.add(var5);
         }
      }

      return var3;
   }

   @Override
   public ISegmentInformation getSection(int var1) {
      return (ISegmentInformation)this.secInfo.get(var1);
   }

   @Override
   public int getSectionCount() {
      return this.secInfo.size();
   }

   protected void addSection(ISegmentInformation var1) {
      this.secInfo.add(var1);
   }

   protected void insertSection(int var1, ISegmentInformation var2) {
      this.secInfo.add(var1, var2);
   }

   protected void addAllSections(Collection var1) {
      this.secInfo.addAll(var1);
   }

   protected void removeSection(int var1) {
      this.secInfo.remove(var1);
   }

   @Override
   public Collection getSymbols() {
      return this.symInfo;
   }

   public int getSymbolCount() {
      return this.symInfo.size();
   }

   protected boolean addSymbol(ISymbolInformation var1) {
      return this.symInfo.add(var1);
   }

   protected void addAllSymbols(Collection var1) {
      for (ISymbolInformation var3 : var1) {
         this.addSymbol(var3);
      }
   }

   public List getImportedSymbols() {
      return this.getSymbols(1, 0);
   }

   public List getExportedSymbols() {
      return this.getSymbols(2, 0);
   }

   public List getSymbols(int var1, int var2) {
      ArrayList var3 = new ArrayList();

      for (ISymbolInformation var5 : this.getSymbols()) {
         int var6 = var5.getFlags();
         if ((var6 & var1) == var1 && (var6 & var2) == 0) {
            var3.add(var5);
         }
      }

      return var3;
   }

   @Override
   public long convertFileOffsetToRelativeAddress(long var1) {
      for (ISegmentInformation var4 : this.getSegments()) {
         long var5 = var4.getOffsetInFile();
         if (var1 >= var5 && var1 < var5 + var4.getSizeInFile()) {
            return var4.getOffsetInMemory() + (var1 - var5);
         }
      }

      return -1L;
   }

   @Override
   public long convertRelativeAddressToFileOffset(long var1) {
      for (ISegmentInformation var4 : this.getSegments()) {
         long var5 = var4.getOffsetInMemory();
         if (Longs.compareUnsigned(var1, var5) >= 0 && Longs.compareUnsigned(var1, var5 + var4.getSizeInMemory()) < 0) {
            return var4.getOffsetInFile() + (var1 - var5);
         }
      }

      return -1L;
   }

   @Override
   public synchronized IVirtualMemory getRawMemoryMappedImage() {
      if (this.rawmem == null) {
         this.rawmem = this.createSuitableMemory();
         this.mapRawNoReloc(this.rawmem, -1L);
      }

      return this.rawmem;
   }

   @Override
   public boolean map(IVirtualMemory var1, long var2, boolean var4, ILinkInfoProvider var5) {
      if (var1 == null) {
         throw new NullPointerException("A memory must be provided");
      } else {
         long var6 = var2 != -1L ? var2 : this.getLoaderInformation().getImageBase();
         if (!this.mapRawNoReloc(var1, var6)) {
            return false;
         } else {
            if (var4) {
               this.applyRelocations(var1, var6, var5);
            }

            return true;
         }
      }
   }

   protected IVirtualMemory createSuitableMemory() {
      return new awp(this.getLoaderInformation().getWordSize(), 12, this.getLoaderInformation().getEndianness());
   }

   protected boolean mapRawNoReloc(IVirtualMemory var1, long var2) {
      if (var2 == -1L) {
         var2 = this.getLoaderInformation().getImageBase();
      }

      if (this.shouldAllocateFullImage()) {
         long var4 = this.getLoaderInformation().getImageSize();
         Assert.a(Longs.compareUnsigned(var4, 4294967296L) < 0, "Current mapper limitation: Cannot map more than 4Gb");
         Assert.a(var1.isValidAddress(var2 + var4), "Cannot allocate whole object in memory space");
         VirtualMemoryUtil.allocateFillGaps(var1, var2, (int)var4, 7);
      }

      try {
         boolean var21;
         try (SeekableByteChannel var20 = this.getMappableInput().getChannel()) {
            for (ISegmentInformation var7 : this.getValidSegments()) {
               if ((var7.getFlags() & 1073741824) == 0) {
                  long var8 = var2 + var7.getOffsetInMemory();
                  long var10 = var7.getSizeInMemory();
                  Assert.a(Longs.compareUnsigned(var10, 4294967296L) < 0, "Current mapper limitation: Cannot map more than 4Gb");
                  VirtualMemoryUtil.allocateFillGaps(var1, var8, (int)var10, 7);
                  int var12 = (int)var7.getSizeInFile();
                  ByteBuffer var5 = ByteBuffer.allocate(var12);
                  var20.position(var7.getOffsetInFile());
                  int var13 = var20.read(var5);
                  if (var13 != var12) {
                     Object[] var10000 = new Object[]{var13, var12};
                  }

                  if (Longs.compareUnsigned(var10, var7.getSizeInFile()) < 0) {
                     var12 = (int)var10;
                  }

                  int var14 = var1.write(var8, var12, var5.array(), 0);
                  if (var14 != var12) {
                     throw new MemoryException(Strings.ff("Partial write: actual=%Xh, expected=%Xh", var14, var12));
                  }
               }
            }

            var21 = true;
         }

         return var21;
      } catch (MemoryException var17) {
         logger.catchingSilent(var17);
         JebCoreService.notifySilentExceptionToClient(var17);
         return false;
      } catch (IOException var18) {
         return false;
      } catch (Exception var19) {
         logger.catchingSilent(var19);
         JebCoreService.notifySilentExceptionToClient(var19);
         return false;
      }
   }

   protected IInput getMappableInput() {
      return this.getInput();
   }

   protected abstract boolean shouldAllocateFullImage();

   protected abstract boolean applyRelocations(IVirtualMemory var1, long var2, ILinkInfoProvider var4);
}
