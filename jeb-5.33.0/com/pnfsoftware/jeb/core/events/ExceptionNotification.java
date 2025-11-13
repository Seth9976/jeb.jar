package com.pnfsoftware.jeb.core.events;

import java.util.Map;

public class ExceptionNotification extends ClientNotification {
   public static final int FLAG_SILT = 1;
   public static final int FLAG_FUPL = 2;
   public static final int FLAG_DNUS = 4;
   private Throwable throwable;
   private int flags;
   private Map extradata;

   public ExceptionNotification(Throwable var1, String var2, int var3, Map var4) {
      super(var2 != null ? var2 : var1.getMessage(), ClientNotificationLevel.ERROR);
      this.throwable = var1;
      this.flags = var3;
      this.extradata = var4;
   }

   public ExceptionNotification(Throwable var1, String var2, int var3) {
      this(var1, var2, var3, null);
   }

   public ExceptionNotification(Throwable var1, String var2) {
      this(var1, var2, 0);
   }

   public Throwable getThrowable() {
      return this.throwable;
   }

   public int getFlags() {
      return this.flags;
   }

   public Map getExtraData() {
      return this.extradata;
   }
}
