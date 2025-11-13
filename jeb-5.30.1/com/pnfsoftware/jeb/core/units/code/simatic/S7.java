package com.pnfsoftware.jeb.core.units.code.simatic;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerStaticOk;
import java.util.Calendar;
import java.util.TimeZone;

public class S7 {
   public static final int MASK_CAT = 7;
   public static final int FLAG_HAS_INIT_DATA = 8;
   public static final int FLAG_HAS_EXTENDED_INFO = 16;
   public static final int CAT_IN = 1;
   public static final int CAT_OUT = 2;
   public static final int CAT_IN_OUT = 3;
   public static final int CAT_STATIC = 4;
   public static final int CAT_TEMP = 5;
   public static final int CAT_RET = 6;

   @Ser
   public static enum AreaType {
      UNKNOWN(0),
      PI_PQ(128),
      I(129),
      Q(130),
      M(131),
      DB(132),
      DI(133),
      L(134),
      V(135);

      final int id;

      private AreaType(int var3) {
         this.id = var3;
      }

      public int getId() {
         return this.id;
      }

      public static S7.AreaType fromId(int var0) {
         for (S7.AreaType var4 : values()) {
            if (var4.id == var0) {
               return var4;
            }
         }

         return UNKNOWN;
      }

      public boolean isAnyOf(S7.AreaType... var1) {
         for (S7.AreaType var5 : var1) {
            Assert.a(var5 != null);
            if (var5 == this) {
               return true;
            }
         }

         return false;
      }
   }

   @Ser
   public static enum BlockType {
      UNKNOWN(0),
      DI(5),
      OB(8),
      DB(10),
      SDB(11),
      FC(12),
      SFC(13),
      FB(14),
      SFB(15);

      final int id;

      private BlockType(int var3) {
         this.id = var3;
      }

      public int getId() {
         return this.id;
      }

      public static S7.BlockType fromId(int var0) {
         for (S7.BlockType var4 : values()) {
            if (var4.id == var0) {
               return var4;
            }
         }

         return UNKNOWN;
      }

      public boolean isAnyOf(S7.BlockType... var1) {
         for (S7.BlockType var5 : var1) {
            Assert.a(var5 != null);
            if (var5 == this) {
               return true;
            }
         }

         return false;
      }

      public boolean isNoneOf(S7.BlockType... var1) {
         for (S7.BlockType var5 : var1) {
            Assert.a(var5 != null);
            if (var5 == this) {
               return false;
            }
         }

         return true;
      }

      public boolean isLogicBlock() {
         switch (this) {
            case OB:
            case FB:
            case FC:
            case SFB:
            case SFC:
               return true;
            default:
               return false;
         }
      }

      public boolean isDataBlock() {
         switch (this) {
            case DI:
            case DB:
            case SDB:
               return true;
            default:
               return false;
         }
      }
   }

   @Ser
   public static enum DataType {
      UNKNOWN(0, 0),
      BOOL(1, 1, false),
      BYTE(2, 8, 0),
      CHAR(3, 8, ' '),
      WORD(4, 16, (short)0),
      INT(5, 16, (short)0),
      DWORD(6, 32, 0),
      DINT(7, 32, 0),
      REAL(8, 32, 0.0F),
      DATE(9, 16, S7.Date.DEFAULT),
      TIME_OF_DAY(10, 32, S7.TimeOfDay.DEFAULT),
      TIME(11, 32, S7.Time.DEFAULT),
      S5TIME(12, 16, S7.S5Time.DEFAULT),
      DATE_AND_TIME(14, 64, S7.DateAndTime.DEFAULT),
      ARRAY(16, -1),
      STRUCT(17, -1),
      STRING(19, 16),
      POINTER(20, 48),
      MULTI_INST_FB(21, -1),
      ANY(22, 80),
      BLOCK_FB(23, 16),
      BLOCK_FC(24, 16),
      BLOCK_DB(25, 16),
      BLOCK_SDB(26, 16),
      MULTI_INST_SFB(27, -1),
      COUNTER(28, 16),
      TIMER(29, 16);

      final int id;
      final int bitsize;
      final Object defaultValue;

      private DataType(int var3, int var4, Object var5) {
         this.id = var3;
         this.bitsize = var4;
         this.defaultValue = var5;
      }

      private DataType(int var3, int var4) {
         this.id = var3;
         this.bitsize = var4;
         this.defaultValue = null;
      }

      public int getId() {
         return this.id;
      }

      public int getBitsize() {
         return this.bitsize;
      }

      public static S7.DataType fromId(int var0) {
         for (S7.DataType var4 : values()) {
            if (var4.id == var0) {
               return var4;
            }
         }

         return UNKNOWN;
      }
   }

   @Ser
   public static class Date {
      @SerStaticOk
      public static final S7.Date DEFAULT = create(0);
      @SerId(1)
      private int raw;
      @SerId(2)
      private String str;

      public static S7.Date create(int var0) {
         return new S7.Date(var0);
      }

      private Date(int var1) {
         this.raw = var1 & 65535;
         int var2 = var1 * 86400 + 631152000;
         Calendar var3 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
         var3.setTime(new java.util.Date(1000L * var2));
         int var4 = var3.get(1);
         int var5 = 1 + var3.get(2);
         int var6 = var3.get(5);
         this.str = Strings.ff("D#%d-%d-%d", var4, var5, var6);
      }

      public short getRawValue() {
         return (short)this.raw;
      }

      @Override
      public String toString() {
         return this.str;
      }
   }

   @Ser
   public static class DateAndTime {
      @SerStaticOk
      public static final S7.DateAndTime DEFAULT = create(-8070167957759590398L);
      @SerId(1)
      private long raw;
      @SerId(2)
      private String str;

      public static S7.DateAndTime create(long var0) {
         return new S7.DateAndTime(var0);
      }

      private DateAndTime(long var1) {
         this.raw = var1;
         int var3 = (int)(var1 >>> 56 & 15L);
         var3 += 10 * (int)(var1 >>> 60 & 15L);
         if (var3 >= 90) {
            var3 += 1900;
         } else {
            var3 += 2000;
         }

         int var4 = (int)(var1 >>> 48 & 15L);
         var4 += 10 * (int)(var1 >>> 52 & 15L);
         int var5 = (int)(var1 >>> 40 & 15L);
         var5 += 10 * (int)(var1 >>> 44 & 15L);
         int var6 = (int)(var1 >>> 32 & 15L);
         var6 += 10 * (int)(var1 >>> 36 & 15L);
         int var7 = (int)(var1 >>> 24 & 15L);
         var7 += 10 * (int)(var1 >>> 28 & 15L);
         int var8 = (int)(var1 >>> 16 & 15L);
         var8 += 10 * (int)(var1 >>> 20 & 15L);
         int var9 = (int)(var1 >>> 8 & 15L);
         var9 += 10 * (int)(var1 >>> 12 & 15L);
         var9 *= 10;
         var9 += (int)(var1 >>> 4 & 15L);
         this.str = Strings.ff("DT#%d-%d-%d-%d:%d:%d.%d", var3, var4, var5, var6, var7, var8, var9);
      }

      public long getRawValue() {
         return this.raw;
      }

      @Override
      public String toString() {
         return this.str;
      }
   }

   @Ser
   public static enum LangType {
      UNKNOWN(0),
      STL(1),
      LAD(2),
      FBD(3),
      SCL(4),
      DB(5),
      GRAPH(6),
      CFC(7),
      SFC(8);

      final int id;

      private LangType(int var3) {
         this.id = var3;
      }

      public int getId() {
         return this.id;
      }

      public static S7.LangType fromId(int var0) {
         for (S7.LangType var4 : values()) {
            if (var4.id == var0) {
               return var4;
            }
         }

         return UNKNOWN;
      }
   }

   @Ser
   public static class S5Time {
      @SerStaticOk
      public static final S7.S5Time DEFAULT = create(0);
      @SerId(1)
      private int raw;
      @SerId(2)
      private String str;

      public static S7.S5Time create(int var0) {
         return new S7.S5Time(var0);
      }

      private S5Time(int var1) {
         var1 &= 16383;
         this.raw = var1;
         int var2 = ((var1 & 3840) >> 8) * 100 + ((var1 & 240) >> 4) * 10 + (var1 & 15);
         int var3 = 0;
         int var4 = 0;
         int var5 = 0;
         int[] var6 = new int[]{10, 100, 1000, 10000};
         int var7 = var1 >> 12 & 3;
         var2 *= var6[var7];
         if (var2 >= 3600000) {
            var5 = var2 / 3600000;
            var2 -= var5 * 3600000;
         }

         if (var2 >= 60000) {
            var4 = var2 / 60000;
            var2 -= var4 * 60000;
         }

         if (var2 >= 1000) {
            var3 = var2 / 1000;
            var2 -= var3 * 1000;
         }

         StringBuilder var8 = new StringBuilder();
         Strings.ff(var8, "S5T#");
         if (var5 > 0) {
            Strings.ff(var8, "%dH", var5);
         }

         if (var4 > 0) {
            Strings.ff(var8, "%dM", var4);
         }

         if (var3 > 0) {
            Strings.ff(var8, "%dS", var3);
         }

         if (var2 > 0 || var5 == 0 && var4 == 0 && var3 == 0) {
            Strings.ff(var8, "%dMS", var2);
         }

         this.str = var8.toString();
      }

      public short getRawValue() {
         return (short)this.raw;
      }

      @Override
      public String toString() {
         return this.str;
      }
   }

   @Ser
   public static enum SectionType {
      UNKNOWN(0),
      IN(1),
      OUT(2),
      IN_OUT(3),
      STAT(4),
      TEMP(5),
      RET(6);

      final int id;

      private SectionType(int var3) {
         this.id = var3;
      }

      public int getId() {
         return this.id;
      }

      public static S7.SectionType fromId(int var0) {
         for (S7.SectionType var4 : values()) {
            if (var4.id == var0) {
               return var4;
            }
         }

         return UNKNOWN;
      }
   }

   @Ser
   public static class StringA {
      public static final int MIN_LEN = 0;
      public static final int MAX_LEN = 254;
      @SerId(1)
      private int maxlen;
      @SerId(2)
      private byte[] stringBytes;

      public StringA(int var1, byte[] var2) {
         Assert.a(var1 >= 0 && var1 <= 254);
         Assert.a(var2 != null && var2.length <= var1);
         this.maxlen = var1;
         this.stringBytes = var2;
      }

      public int getCurrentLength() {
         return this.stringBytes.length;
      }

      public int getMaximumLength() {
         return this.maxlen;
      }

      public int getMemorySizeInBytes() {
         return 2 + this.maxlen;
      }

      public byte[] getStringBytes() {
         return this.stringBytes;
      }

      @Override
      public String toString() {
         return Strings.decodeASCII(this.stringBytes);
      }
   }

   @Ser
   public static class Time {
      @SerStaticOk
      public static final S7.Time DEFAULT = create(0);
      @SerId(1)
      private int raw;
      @SerId(2)
      private String str;

      public static S7.Time create(int var0) {
         return new S7.Time(var0);
      }

      private Time(int var1) {
         this.raw = var1;
         boolean var2 = false;
         if (var1 < 0) {
            var1 = -var1;
            var2 = true;
         }

         long var3 = var1 & -1;
         int var5 = (int)(var3 / 86400000L);
         var3 -= 86400000 * var5;
         int var6 = (int)(var3 / 3600000L);
         var3 -= 3600000 * var6;
         int var7 = (int)(var3 / 60000L);
         var3 -= 60000 * var7;
         int var8 = (int)(var3 / 1000L);
         var3 -= 1000 * var8;
         int var9 = (int)var3;
         StringBuilder var10 = new StringBuilder();
         if (var2) {
            var10.append("-");
         }

         var10.append("T#");
         if (var5 > 0) {
            Strings.ff(var10, "%dD", var5);
         }

         if (var6 > 0) {
            Strings.ff(var10, "%dH", var6);
         }

         if (var7 > 0) {
            Strings.ff(var10, "%dM", var7);
         }

         if (var8 > 0) {
            Strings.ff(var10, "%dS", var8);
         }

         if (var9 > 0 || var5 == 0 && var6 == 0 && var8 == 0) {
            Strings.ff(var10, "%dMS", var9);
         }

         this.str = var10.toString();
      }

      public int getRawValue() {
         return this.raw;
      }

      @Override
      public String toString() {
         return this.str;
      }
   }

   @Ser
   public static class TimeOfDay {
      @SerStaticOk
      public static final S7.TimeOfDay DEFAULT = create(0);
      @SerId(1)
      private int raw;
      @SerId(2)
      private String str;

      public static S7.TimeOfDay create(int var0) {
         return new S7.TimeOfDay(var0);
      }

      private TimeOfDay(int var1) {
         this.raw = var1;
         long var2 = var1 & -1;
         int var4 = (int)(var2 % 1000L);
         var2 /= 1000L;
         int var5 = (int)(var2 % 60L);
         var2 /= 60L;
         int var6 = (int)(var2 % 60L);
         var2 /= 60L;
         int var7 = (int)var2;
         this.str = Strings.ff("TOD#%d:%d:%d.%d", var7, var6, var5, var4);
      }

      public int getRawValue() {
         return this.raw;
      }

      @Override
      public String toString() {
         return this.str;
      }
   }
}
