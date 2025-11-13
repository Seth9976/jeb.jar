package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.client.ErrorLogGenerator;
import com.pnfsoftware.jeb.core.events.ExceptionNotification;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.TelemetryNotification;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.GA;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JebCoreService {
   private static final ILogger logger = GlobalLog.getLogger(JebCoreService.class);

   public static ICoreContext getExistingInstance() {
      return GA.A();
   }

   public static ICoreContext getInstance() throws JebException {
      return GA.pC();
   }

   public static ICoreContext getInstance(String var0) throws JebException {
      return GA.pC(var0, null);
   }

   public static ICoreContext getInstance(String var0, CoreOptions var1) throws JebException {
      return GA.pC(var0, var1);
   }

   public static IEnginesContext getDefaultEnginesContext() {
      return getExistingInstance().getDefaultEnginesContexts();
   }

   public static void notifyExceptionToClient(Throwable var0, int var1, Map var2, IUnit var3) {
      if (var0 != null) {
         try {
            ICoreContext var4 = getInstance();
            if (var2 == null) {
               var2 = new HashMap();
            }

            var2.put("urlencoded-threads", ErrorLogGenerator.generateUrlencodedThreadsRecordValue());
            if (var3 != null) {
               String var5 = UnitUtil.buildFullyQualifiedUnitPath(var3);
               var2.put("unit-path", var5);
            }

            var4.notifyListeners(new JebEvent(J.Notification, new ExceptionNotification(var0, null, var1, (Map)var2)));
         } catch (JebException var6) {
         }
      }
   }

   public static void notifyExceptionToClient(Throwable var0) {
      notifyExceptionToClient(var0, 0, null, null);
   }

   public static void notifySilentExceptionToClient(Throwable var0) {
      notifyExceptionToClient(var0, 1, null, null);
   }

   public static void notifySilentExceptionToClient(Throwable var0, IUnit var1) {
      notifyExceptionToClient(var0, 1, null, var1);
   }

   public static void notifySilentExceptionToClient(Throwable var0, Map var1) {
      notifyExceptionToClient(var0, 1, var1, null);
   }

   public static void silentExcept(Throwable var0) {
      notifySilentExceptionToClient(var0);
   }

   public static void notifyTelemetryToClient(String var0, Map var1) {
      if (var0 != null) {
         try {
            if (var1 == null) {
               var1 = Collections.emptyMap();
            }

            getInstance().notifyListeners(new JebEvent(J.Notification, new TelemetryNotification(var0, var1)));
         } catch (JebException var2) {
         }
      }
   }

   public static void notifyTelemetryToClient(String var0) {
      HashMap var1 = new HashMap();
      notifyTelemetryToClient(var0, var1);
   }

   public static void notifyTelemetryToClient(String var0, String var1, String var2) {
      HashMap var3 = new HashMap();
      var3.put(var1, var2);
      notifyTelemetryToClient(var0, var3);
   }

   public static void notifyTelemetryToClient(String var0, String var1, String var2, String var3, String var4) {
      HashMap var5 = new HashMap();
      var5.put(var1, var2);
      var5.put(var3, var4);
      notifyTelemetryToClient(var0, var5);
   }
}
