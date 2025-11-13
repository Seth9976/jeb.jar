package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Ser
public class StorageEntry {
   @SerId(1)
   private StorageEntry.Type type;
   @SerId(2)
   private long value;
   @SerId(3)
   private long value2;
   @SerId(4)
   private long value3;
   @SerId(5)
   private long value4;
   @SerId(6)
   private List items;
   @SerId(7)
   private StorageEntry.TransformationRule transformation;

   public static StorageEntry createStackSlot(long var0) {
      return createStackEntry(var0, 1);
   }

   public static StorageEntry createStackEntry(long var0, int var2) {
      return new StorageEntry(StorageEntry.Type.STACK, var0, var2, 0L, 0L, null);
   }

   public static StorageEntry createRegister(long var0) {
      if (var0 < 0L) {
         throw new RuntimeException("Illegal register index for slot: " + var0);
      } else {
         return new StorageEntry(StorageEntry.Type.REGISTER, var0, -1L, 0L, 0L, null);
      }
   }

   public static StorageEntry createRegisterPair(long var0, long var2) {
      return createRegisterPair(var0, var2, false);
   }

   public static StorageEntry createRegisterPairEndianDep(long var0, long var2) {
      return createRegisterPair(var0, var2, true);
   }

   private static StorageEntry createRegisterPair(long var0, long var2, boolean var4) {
      return new StorageEntry(
         StorageEntry.Type.REGISTER_PAIR, var0, var2, 0L, 0L, var4 ? StorageEntry.TransformationRule.ENDIAN_DEPENDENT : StorageEntry.TransformationRule.NONE
      );
   }

   public static StorageEntry createRegisterQuad(long var0, long var2, long var4, long var6) {
      return createRegisterQuad(var0, var2, var4, var6, false);
   }

   public static StorageEntry createRegisterQuadEndianDep(long var0, long var2, long var4, long var6) {
      return createRegisterQuad(var0, var2, var4, var6, true);
   }

   private static StorageEntry createRegisterQuad(long var0, long var2, long var4, long var6, boolean var8) {
      return new StorageEntry(
         StorageEntry.Type.REGISTER_QUAD,
         var0,
         var2,
         var4,
         var6,
         var8 ? StorageEntry.TransformationRule.ENDIAN_DEPENDENT : StorageEntry.TransformationRule.NONE
      );
   }

   public static StorageEntry createMixed(StorageEntry... var0) {
      return createMixed(Arrays.asList(var0));
   }

   public static StorageEntry createMixed(Collection var0) {
      if (var0 != null && var0.size() >= 2) {
         int var1 = var0.size() - 1;
         int var2 = 0;

         for (StorageEntry var4 : var0) {
            if (var4.getType() != StorageEntry.Type.REGISTER && (var2 != var1 || var4.getType() != StorageEntry.Type.STACK)) {
               throw new IllegalArgumentException();
            }

            var2++;
         }

         StorageEntry var5 = new StorageEntry(StorageEntry.Type.MIXED, 0L, 0L, 0L, 0L, null);
         var5.items = new ArrayList(var0);
         return var5;
      } else {
         throw new IllegalArgumentException();
      }
   }

   private StorageEntry(StorageEntry.Type var1, long var2, long var4, long var6, long var8, StorageEntry.TransformationRule var10) {
      if (var1 == null) {
         throw new NullPointerException("Missing slot type");
      } else {
         this.type = var1;
         this.value = var2;
         this.value2 = var4;
         this.value3 = var6;
         this.value4 = var8;
         this.transformation = var10;
      }
   }

   public StorageEntry withCount(int var1) {
      int var2 = this.getValueAsStackIndex();
      return new StorageEntry(StorageEntry.Type.STACK, var2, var1, 0L, 0L, this.transformation);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.items == null ? 0 : this.items.hashCode());
      var1 = 31 * var1 + (this.transformation == null ? 0 : this.transformation.hashCode());
      var1 = 31 * var1 + (this.type == null ? 0 : this.type.hashCode());
      var1 = 31 * var1 + (int)(this.value ^ this.value >>> 32);
      var1 = 31 * var1 + (int)(this.value2 ^ this.value2 >>> 32);
      var1 = 31 * var1 + (int)(this.value3 ^ this.value3 >>> 32);
      return 31 * var1 + (int)(this.value4 ^ this.value4 >>> 32);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         StorageEntry var2 = (StorageEntry)var1;
         if (this.items == null) {
            if (var2.items != null) {
               return false;
            }
         } else if (!this.items.equals(var2.items)) {
            return false;
         }

         if (this.transformation != var2.transformation) {
            return false;
         } else if (this.type != var2.type) {
            return false;
         } else if (this.value != var2.value) {
            return false;
         } else if (this.value2 != var2.value2) {
            return false;
         } else {
            return this.value3 != var2.value3 ? false : this.value4 == var2.value4;
         }
      }
   }

   public StorageEntry.Type getType() {
      return this.type;
   }

   public int getValueAsStackIndex() {
      Assert.a(this.type == StorageEntry.Type.STACK);
      return (int)this.value;
   }

   public Collection getRegisters() {
      HashSet var1 = new HashSet();
      this.collectRegisters(var1);
      return var1;
   }

   public void collectRegisters(Set var1) {
      switch (this.type) {
         case REGISTER:
            var1.add(this.getValue());
            break;
         case REGISTER_PAIR:
            var1.add(this.getValue());
            var1.add(this.getValue2());
            break;
         case REGISTER_QUAD:
            var1.add(this.getValue());
            var1.add(this.getValue2());
            var1.add(this.getValue3());
            var1.add(this.getValue4());
            break;
         case MIXED:
            for (StorageEntry var3 : this.items) {
               var3.collectRegisters(var1);
            }
      }
   }

   public long getValue() {
      return this.value;
   }

   public long getValue(Endianness var1) {
      if (this.transformation == StorageEntry.TransformationRule.ENDIAN_DEPENDENT) {
         return var1 == Endianness.LITTLE_ENDIAN ? this.value : this.value2;
      } else {
         return this.value;
      }
   }

   public long getValue2() {
      return this.value2;
   }

   public long getValue2(Endianness var1) {
      if (this.transformation == StorageEntry.TransformationRule.ENDIAN_DEPENDENT) {
         return var1 == Endianness.LITTLE_ENDIAN ? this.value2 : this.value;
      } else {
         return this.value2;
      }
   }

   public long getValue3() {
      return this.value3;
   }

   public long getValue4() {
      return this.value4;
   }

   public List getMixedItems() {
      Assert.a(this.type == StorageEntry.Type.MIXED);
      return Collections.unmodifiableList(this.items);
   }

   public StorageEntry getMixedItem(int var1) {
      Assert.a(this.type == StorageEntry.Type.MIXED);
      return (StorageEntry)this.items.get(var1);
   }

   public int nextSlotIndex(int var1, int var2) {
      return switch (this.type) {
         case REGISTER -> var1 + 1;
         case REGISTER_PAIR -> var1 + 2;
         case REGISTER_QUAD -> var1 + 4;
         default -> var1 + var2;
      };
   }

   public boolean isStackBased() {
      if (this.getType() == StorageEntry.Type.STACK) {
         return true;
      } else {
         if (this.getType() == StorageEntry.Type.MIXED) {
            for (StorageEntry var2 : this.items) {
               if (var2.isStackBased()) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean isRegisterBased() {
      if (this.getType() == StorageEntry.Type.REGISTER) {
         return true;
      } else if (this.getType() == StorageEntry.Type.REGISTER_PAIR) {
         return true;
      } else if (this.getType() == StorageEntry.Type.REGISTER_QUAD) {
         return true;
      } else {
         if (this.getType() == StorageEntry.Type.MIXED) {
            for (StorageEntry var2 : this.items) {
               if (var2.isRegisterBased()) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public StorageEntry nextStackEntry(int var1) {
      return this.nextStackEntry(var1, 1);
   }

   public StorageEntry nextStackEntry(int var1, int var2) {
      if (this.type != StorageEntry.Type.STACK) {
         if (this.type == StorageEntry.Type.MIXED) {
            StorageEntry var6 = (StorageEntry)this.items.get(this.items.size() - 1);
            return var6.nextStackEntry(var1, var2);
         } else {
            throw new IllegalArgumentException();
         }
      } else {
         int var3 = this.getValueAsStackIndex();
         int var4 = this.getSlotCount();
         int var5 = var3 + var4;
         if (var2 >= 2) {
            var5 = (var5 + var2 - 1) / var2 * var2;
         }

         return createStackEntry(var5, var1);
      }
   }

   public int getSlotCount() {
      switch (this.type) {
         case REGISTER:
            return 1;
         case REGISTER_PAIR:
            return 2;
         case REGISTER_QUAD:
            return 4;
         case MIXED:
            int var1 = 0;

            for (StorageEntry var3 : this.items) {
               var1 += var3.getSlotCount();
            }

            return var1;
         case STACK:
            return this.value2 > 0L ? (int)this.value2 : 1;
         default:
            return 0;
      }
   }

   @Override
   public String toString() {
      switch (this.getType()) {
         case REGISTER:
            return Strings.ff("reg=%Xh", this.getValue());
         case REGISTER_PAIR:
            return Strings.ff("pair=%Xh:%Xh", this.getValue(), this.getValue2());
         case REGISTER_QUAD:
            return Strings.ff("quad=%Xh:%Xh:%Xh:%Xh", this.getValue(), this.getValue2(), this.getValue3(), this.getValue4());
         case MIXED:
            return Strings.ff("composite:%s", Strings.join(",", this.items));
         case STACK:
            return Strings.ff("stk=slot:%d,+%d", this.getValue(), this.getValue2());
         default:
            return Strings.ff("other");
      }
   }

   public String formatLong(IRegisterBank var1) {
      StringBuilder var2 = new StringBuilder();
      switch (this.getType()) {
         case REGISTER:
            Strings.ff(var2, "%s", var1.getDescriptionEntryById(this.getValue()).getName());
            break;
         case REGISTER_PAIR:
            Strings.ff(var2, "%s:%s", var1.getDescriptionEntryById(this.getValue()).getName(), var1.getDescriptionEntryById(this.getValue2()).getName());
            break;
         case REGISTER_QUAD:
            Strings.ff(
               var2,
               "%s:%s:%s:%s",
               var1.getDescriptionEntryById(this.getValue()).getName(),
               var1.getDescriptionEntryById(this.getValue2()).getName(),
               var1.getDescriptionEntryById(this.getValue3()).getName(),
               var1.getDescriptionEntryById(this.getValue4()).getName()
            );
         case MIXED:
         default:
            break;
         case STACK:
            Strings.ff(var2, "stack @%d", this.getValue());
      }

      if (this.transformation != null && this.transformation != StorageEntry.TransformationRule.NONE) {
         Strings.ff(var2, " (%s)", this.transformation);
      }

      return var2.toString();
   }

   public String formatParseable(IRegisterBank var1) {
      StringBuilder var2 = new StringBuilder();
      switch (this.getType()) {
         case REGISTER:
            Strings.ff(var2, "%s", var1.getDescriptionEntryById(this.getValue()).getName());
            break;
         case REGISTER_PAIR:
            Strings.ff(var2, "%s+%s", var1.getDescriptionEntryById(this.getValue()).getName(), var1.getDescriptionEntryById(this.getValue2()).getName());
            break;
         case REGISTER_QUAD:
            Strings.ff(
               var2,
               "%s+%s+%s+%s",
               var1.getDescriptionEntryById(this.getValue()).getName(),
               var1.getDescriptionEntryById(this.getValue2()).getName(),
               var1.getDescriptionEntryById(this.getValue3()).getName(),
               var1.getDescriptionEntryById(this.getValue4()).getName()
            );
         case MIXED:
         default:
            break;
         case STACK:
            Strings.ff(var2, "stack@%d", this.getValue());
      }

      if (this.transformation != null && this.transformation != StorageEntry.TransformationRule.NONE) {
         if (this.transformation == StorageEntry.TransformationRule.ENDIAN_DEPENDENT) {
            var2.append("%END%");
         } else {
            var2.append("%").append(this.transformation).append("%");
         }
      }

      return var2.toString();
   }

   @Ser
   public static enum TransformationRule {
      NONE,
      ENDIAN_DEPENDENT;
   }

   @Ser
   public static enum Type {
      STACK,
      REGISTER,
      REGISTER_PAIR,
      REGISTER_QUAD,
      MIXED;
   }
}
