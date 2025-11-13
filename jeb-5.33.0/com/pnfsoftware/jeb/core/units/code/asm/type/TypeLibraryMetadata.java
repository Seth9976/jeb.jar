package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class TypeLibraryMetadata {
   @SerId(1)
   ProcessorType primaryProcessorType;
   @SerId(2)
   int groupId;
   @SerId(3)
   int version;
   @SerId(4)
   String name;
   @SerId(5)
   String description;
   @SerId(6)
   String author;
   @SerId(7)
   long creationTimestamp;
   @SerId(8)
   double priorityOrder;
   @SerId(9)
   List addProcessorTypes;
   @SerId(10)
   List subsystemTypes;
   @SerId(11)
   CompilerType compilerType;
   @SerId(12)
   private int uuid;
   @SerId(13)
   private Map datamap;
   @SerId(14)
   private int standardPackingAlignment;
   @SerTransient
   private volatile List cachedProcessorTypes;

   public static TypeLibraryMetadata create(ProcessorType var0, SubsystemType var1, CompilerType var2) {
      return create(var0, var1, var2, Integer.MAX_VALUE, 0, 0.0, 0, 0, "", "", "");
   }

   public static TypeLibraryMetadata create(
      ProcessorType var0, SubsystemType var1, CompilerType var2, int var3, int var4, double var5, int var7, int var8, String var9, String var10, String var11
   ) {
      ArrayList var12 = new ArrayList();
      if (var0 != null) {
         var12.add(var0);
      }

      ArrayList var13 = new ArrayList();
      if (var1 != null) {
         var13.add(var1);
      }

      return create(var12, var13, var2, var3, var4, var5, var7, var8, var9, var10, var11);
   }

   public static TypeLibraryMetadata create(
      List var0, List var1, CompilerType var2, int var3, int var4, double var5, int var7, int var8, String var9, String var10, String var11
   ) {
      TypeLibraryMetadata var12 = new TypeLibraryMetadata(var0, var1, var2, var4, var5);
      var12.standardPackingAlignment = var3;
      var12.uuid = var7;
      var12.version = var8;
      var12.name = var9;
      var12.description = var10;
      var12.author = var11;
      var12.creationTimestamp = System.currentTimeMillis() / 1000L;
      var12.verifyUuid();
      return var12;
   }

   private TypeLibraryMetadata(List var1, List var2, CompilerType var3, int var4, double var5) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         if (var1.size() >= 1) {
            this.primaryProcessorType = (ProcessorType)var1.get(0);
         }

         if (var1.size() >= 2) {
            this.addProcessorTypes = new ArrayList(var1.subList(1, var1.size()));
         }

         if (var2 != null) {
            this.subsystemTypes = new ArrayList(var2);
         }

         this.compilerType = var3;
         this.groupId = var4;
         this.priorityOrder = var5;
      }
   }

   private void verifyUuid() {
      if (this.uuid == 0 && this.name != null) {
         this.uuid = Hash.calculateCRC32(Strings.encodeUTF8(this.name));
      }
   }

   public ProcessorType getPrimaryProcessorType() {
      return this.primaryProcessorType;
   }

   public List getProcessorTypes() {
      if (this.cachedProcessorTypes == null) {
         synchronized (this) {
            if (this.cachedProcessorTypes == null) {
               this.cachedProcessorTypes = new ArrayList();
               if (this.primaryProcessorType != null) {
                  this.cachedProcessorTypes.add(this.primaryProcessorType);
               }

               if (this.addProcessorTypes != null) {
                  this.cachedProcessorTypes.addAll(this.addProcessorTypes);
               }
            }
         }
      }

      return this.cachedProcessorTypes;
   }

   public SubsystemType getPrimarySubsystemType() {
      return this.subsystemTypes != null && !this.subsystemTypes.isEmpty() ? (SubsystemType)this.subsystemTypes.get(0) : null;
   }

   public List getSubsystemTypes() {
      return this.subsystemTypes == null ? Collections.emptyList() : this.subsystemTypes;
   }

   public CompilerType getCompilerType() {
      return this.compilerType;
   }

   public int getGroupId() {
      return this.groupId;
   }

   public double getPriorityOrder() {
      return this.priorityOrder;
   }

   public int getUuid() {
      return this.uuid;
   }

   public int getVersion() {
      return this.version;
   }

   public String getName() {
      return this.name;
   }

   public String getDescription() {
      return this.description;
   }

   public String getAuthor() {
      return this.author;
   }

   public long getCreationTimestamp() {
      return this.creationTimestamp;
   }

   public synchronized void putData(Map var1) {
      if (this.datamap == null) {
         this.datamap = new HashMap();
      }

      this.datamap.putAll(var1);
   }

   public synchronized void putData(String var1, Object var2) {
      if (this.datamap == null) {
         this.datamap = new HashMap();
      }

      this.datamap.put(var1, var2);
   }

   public synchronized Object getData(String var1) {
      return this.datamap == null ? null : this.datamap.get(var1);
   }

   public int getStandardPackingAlignment() {
      return this.standardPackingAlignment;
   }

   @Override
   public String toString() {
      return Strings.ff(
         "%s/%d/%f(%d/%s/%s/%s/%d)",
         this.getProcessorTypes(),
         this.groupId,
         this.priorityOrder,
         this.version,
         this.name,
         this.description,
         this.author,
         this.creationTimestamp
      );
   }
}
