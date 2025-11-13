package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import javax.annotation.concurrent.Immutable;

@Ser
@Immutable
public class DexDecompilerEvent {
   @SerId(1)
   private long timestamp = System.currentTimeMillis();
   @SerId(2)
   private DexDecompilerEvent.Type type;
   @SerId(3)
   private Object data;
   @SerId(4)
   private String address;

   public DexDecompilerEvent(DexDecompilerEvent.Type var1, Object var2, String var3) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         this.type = var1;
         this.data = var2;
         this.address = var3;
      }
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public DexDecompilerEvent.Type getType() {
      return this.type;
   }

   public Object getData() {
      return this.data;
   }

   protected String formatData(Object var1) {
      return var1.toString();
   }

   public String getAddress() {
      return this.address;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.address == null ? 0 : this.address.hashCode());
      var1 = 31 * var1 + (this.data == null ? 0 : this.data.hashCode());
      return 31 * var1 + (this.type == null ? 0 : this.type.hashCode());
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
         DexDecompilerEvent var2 = (DexDecompilerEvent)var1;
         if (this.address == null) {
            if (var2.address != null) {
               return false;
            }
         } else if (!this.address.equals(var2.address)) {
            return false;
         }

         if (this.data == null) {
            if (var2.data != null) {
               return false;
            }
         } else if (!this.data.equals(var2.data)) {
            return false;
         }

         return this.type == var2.type;
      }
   }

   @Override
   public String toString() {
      return this.format(true, true);
   }

   public String format(boolean var1, boolean var2) {
      StringBuilder var3 = new StringBuilder();
      if (var1) {
         var3.append(this.type);
      }

      if (this.data != null) {
         if (var3.length() > 0) {
            var3.append(": ");
         }

         var3.append(this.formatData(this.data));
      }

      if (var2 && this.address != null) {
         var3.append(" @").append(this.address);
      }

      return var3.toString();
   }

   @Ser
   public static class BuiltString extends DexDecompilerEvent {
      public BuiltString(String var1, String var2) {
         super(DexDecompilerEvent.Type.BUILT_STRING, var1, var2);
      }

      protected String formatData(String var1) {
         return "\"" + Formatter.escapeString(var1) + "\"";
      }
   }

   @Ser
   public static class DecryptedBytes extends DexDecompilerEvent {
      public DecryptedBytes(byte[] var1, String var2) {
         super(DexDecompilerEvent.Type.DECRYPTED_BYTES, var1, var2);
      }

      protected String formatData(byte[] var1) {
         return "\"" + Formatter.escapeBytes(var1) + "\"";
      }
   }

   @Ser
   public static class DecryptedString extends DexDecompilerEvent {
      public DecryptedString(String var1, String var2) {
         super(DexDecompilerEvent.Type.DECRYPTED_STRING, var1, var2);
      }

      protected String formatData(String var1) {
         return "\"" + Formatter.escapeString(var1) + "\"";
      }
   }

   @Ser
   public static class Message extends DexDecompilerEvent {
      public Message(String var1, String var2) {
         super(DexDecompilerEvent.Type.MESSAGE, var1, var2);
      }

      protected String formatData(String var1) {
         return var1;
      }
   }

   @Ser
   public static enum Type {
      BUILT_STRING,
      DECRYPTED_STRING,
      DECRYPTED_BYTES,
      UNREFLECTED_INVOCATION,
      UNREFLECTED_FIELD_READ,
      UNREFLECTED_FIELD_WRITE,
      MESSAGE;
   }

   @Ser
   public static class UnreflectedFieldRead extends DexDecompilerEvent {
      public UnreflectedFieldRead(String var1, String var2) {
         super(DexDecompilerEvent.Type.UNREFLECTED_FIELD_READ, var1, var2);
      }

      protected String formatData(String var1) {
         return var1;
      }
   }

   @Ser
   public static class UnreflectedFieldWrite extends DexDecompilerEvent {
      public UnreflectedFieldWrite(String var1, String var2) {
         super(DexDecompilerEvent.Type.UNREFLECTED_FIELD_WRITE, var1, var2);
      }

      protected String formatData(String var1) {
         return var1;
      }
   }

   @Ser
   public static class UnreflectedInvocation extends DexDecompilerEvent {
      public UnreflectedInvocation(String var1, String var2) {
         super(DexDecompilerEvent.Type.UNREFLECTED_INVOCATION, var1, var2);
      }

      protected String formatData(String var1) {
         return var1;
      }
   }
}
