package com.pnfsoftware.jeb.client.telemetry;

import com.pnfsoftware.jeb.client.AbstractContext;
import com.pnfsoftware.jeb.util.concurrent.ThreadUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.ckx;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class StandardTelemetryDatabase implements ITelemetryDatabase {
   private static final ILogger logger = GlobalLog.getLogger(StandardTelemetryDatabase.class);
   private static final String version = AbstractContext.app_ver.toString();
   private static final String jtdMarker = "JTD1";
   private static final String properMarker = ckx.pC(new byte[]{16, 62, 60, 16, 6, 12, 71, 14, 27, 82, 69, 2, 93, 0, 2, 57}, 2, 163);
   private static final int defaultDumpPeriodSeconds = 300;
   private static final int defaultPurgeThreshold = 100000;
   private ITelemetryEndpoint endpoint;
   private File dbfile;
   private Connection connection = null;
   private Thread threadDumper;
   private volatile int dumpPeriodSeconds = 300;
   private volatile int purgeThreshold = 100000;
   private int errcnt;

   public StandardTelemetryDatabase(String var1, ITelemetryEndpoint var2) throws IOException, TelemetryException {
      this(var1, var2, false);
   }

   public StandardTelemetryDatabase(String var1, ITelemetryEndpoint var2, boolean var3) throws IOException, TelemetryException {
      if (var2 == null) {
         throw new IllegalArgumentException("Specify an endpoint");
      } else {
         this.endpoint = var2;
         var1 = IO.abs(var1).replace('\\', '/');
         this.dbfile = new File(var1);
         boolean var4 = !this.dbfile.exists() || this.dbfile.length() == 0L;
         if (!var4) {
            try (RandomAccessFile var5 = new RandomAccessFile(this.dbfile, "rw")) {
               byte[] var6 = new byte[16];
               var5.seek(0L);
               var5.readFully(var6);
               if (Strings.decodeASCII(var6).startsWith("JTD1")) {
                  if (16 + EndianUtil.bytesToInt(var6, 4, ByteOrder.BIG_ENDIAN) != this.dbfile.length()) {
                     throw new IOException("Unexpected size");
                  }

                  var5.seek(0L);
                  var5.write(Strings.encodeASCII(properMarker));
                  var5.close();
               }
            } catch (IOException var15) {
               throw new TelemetryException(var15);
            }
         }

         try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + var1);
         } catch (SQLException var11) {
            throw new TelemetryException(var11);
         }

         if (var4) {
            try (Statement var17 = this.connection.createStatement()) {
               String var18 = ckx.pC(
                  new byte[]{
                     12,
                     17,
                     23,
                     4,
                     21,
                     17,
                     101,
                     116,
                     21,
                     3,
                     14,
                     9,
                     101,
                     69,
                     19,
                     19,
                     11,
                     26,
                     7,
                     83,
                     8,
                     92,
                     29,
                     4,
                     8,
                     22,
                     7,
                     21,
                     12,
                     29,
                     80,
                     73,
                     7,
                     26,
                     88,
                     12,
                     86,
                     19,
                     23,
                     1,
                     26,
                     6,
                     1,
                     78,
                     84,
                     17,
                     29,
                     12,
                     88,
                     12,
                     69,
                     19,
                     19,
                     11,
                     26,
                     26,
                     15,
                     12,
                     8,
                     69,
                     84,
                     17,
                     29,
                     12,
                     88,
                     12,
                     80,
                     2,
                     29,
                     31,
                     21,
                     23,
                     6,
                     29,
                     12,
                     22,
                     83,
                     84,
                     17,
                     29,
                     12,
                     93
                  },
                  1,
                  79
               );
               var17.executeUpdate(var18);
            } catch (SQLException var13) {
               try {
                  this.connection.close();
               } catch (SQLException var8) {
               }

               throw new TelemetryException(var13);
            }
         }

         if (var3) {
            this.startDumperThread();
         }
      }
   }

   public void startDumperThread() {
      if (this.threadDumper != null) {
         throw new IllegalStateException();
      } else {
         this.threadDumper = ThreadUtil.start(new StandardTelemetryDatabase$1(this));
      }
   }

   @Override
   protected void finalize() throws Throwable {
      if (this.connection != null) {
         try {
            this.close();
         } catch (Exception var2) {
            logger.catchingSilent(var2);
         }
      }
   }

   @Override
   public synchronized void close() throws TelemetryException {
      if (this.connection == null) {
         throw new IllegalStateException("Already closed.");
      } else {
         if (this.errcnt > 0) {
            this.dbfile.delete();
         }

         try {
            this.connection.close();
         } catch (SQLException var11) {
            throw new TelemetryException(var11);
         } finally {
            this.connection = null;
         }

         if (this.errcnt == 0) {
            int var1 = (int)this.dbfile.length();

            try (RandomAccessFile var2 = new RandomAccessFile(this.dbfile, "rw")) {
               byte[] var3 = new byte[16];
               var2.seek(0L);
               var2.readFully(var3);
               if (Arrays.equals(var3, Strings.encodeASCII(properMarker))) {
                  var2.seek(0L);
                  var2.write(Strings.encodeASCII("JTD1"));
                  var2.writeInt(var1 - 16);
                  var2.writeInt(0);
                  var2.writeInt(0);
                  var2.close();
               }
            } catch (IOException var14) {
               throw new TelemetryException(var14);
            }
         }
      }
   }

   public void setDumpPeriod(int var1) {
      if (var1 <= 0) {
         var1 = 300;
      }

      this.dumpPeriodSeconds = var1;
   }

   public int getDumpPeriod() {
      return this.dumpPeriodSeconds;
   }

   public void setPurgeThreshold(int var1) {
      if (var1 <= 0) {
         var1 = 100000;
      }

      this.purgeThreshold = var1;
   }

   public int getPurgeThreshold() {
      return this.purgeThreshold;
   }

   @Override
   public boolean record(String var1) {
      return this.record(var1, null);
   }

   @Override
   public boolean record(String var1, String var2, String var3) {
      LinkedHashMap var4 = new LinkedHashMap();
      var4.put(var2, var3);
      return this.record(var1, var4);
   }

   @Override
   public boolean record(String var1, String var2, String var3, String var4, String var5) {
      LinkedHashMap var6 = new LinkedHashMap();
      var6.put(var2, var3);
      var6.put(var4, var5);
      return this.record(var1, var6);
   }

   @Override
   public synchronized boolean record(String var1, Map var2) {
      int var3 = (int)(System.currentTimeMillis() / 1000L);

      try {
         boolean var12;
         try (Statement var4 = this.connection.createStatement()) {
            String var5;
            if (var2 != null && !var2.isEmpty()) {
               String var11 = Strings.encodeMap(var2);
               String var7 = ckx.pC("INSERT INTO events(timestamp, version, eventname, properties) VALUES(%d, '%s', '%s', '%s')");
               var5 = Strings.ff(var7, var3, version, var1, var11);
            } else {
               String var6 = ckx.pC(
                  new byte[]{
                     10,
                     33,
                     35,
                     60,
                     32,
                     61,
                     71,
                     33,
                     58,
                     116,
                     103,
                     67,
                     76,
                     86,
                     84,
                     87,
                     77,
                     64,
                     4,
                     84,
                     91,
                     93,
                     87,
                     70,
                     88,
                     65,
                     34,
                     2,
                     77,
                     67,
                     26,
                     0,
                     82,
                     18,
                     7,
                     11,
                     65,
                     67,
                     82,
                     69,
                     31,
                     17,
                     29,
                     84,
                     15,
                     7,
                     11,
                     12,
                     69,
                     73,
                     55,
                     53,
                     41,
                     38,
                     107,
                     115,
                     105,
                     73,
                     8,
                     12,
                     82,
                     78,
                     66,
                     27,
                     83,
                     95,
                     0,
                     85,
                     64,
                     0,
                     66,
                     91
                  },
                  2,
                  106
               );
               var5 = Strings.ff(var6, var3, version, var1);
            }

            var4.executeUpdate(var5);
            var12 = true;
         }

         return var12;
      } catch (Exception var10) {
         this.errcnt++;
         logger.catchingSilent(var10);
         return false;
      }
   }

   public synchronized int size() {
      try {
         int var3;
         try (
            Statement var1 = this.connection.createStatement();
            ResultSet var2 = var1.executeQuery(
               ckx.pC(new byte[]{16, 42, 60, 60, 49, 61, 71, 43, 59, 117, 102, 55, 1, 10, 24, 25, 127, 97, 99, 109, 18, 85, 68, 80, 66, 84, 60}, 2, 143)
            );
         ) {
            var2.next();
            var3 = var2.getInt(1);
         }

         return var3;
      } catch (SQLException var9) {
         this.errcnt++;
         logger.catchingSilent(var9);
         return -1;
      }
   }

   public synchronized boolean clear() {
      try {
         boolean var2;
         try (Statement var1 = this.connection.createStatement()) {
            var1.execute(ckx.pC(new byte[]{37, 1, 9, 9, 17, 17, 101, 102, 20, 29, 2, 109, 69, 19, 19, 11, 26, 7}, 1, 97));
            var2 = true;
         }

         return var2;
      } catch (SQLException var6) {
         this.errcnt++;
         return false;
      }
   }

   public boolean dump() {
      if (!this.endpoint.canDump()) {
         return false;
      } else {
         synchronized (this) {
            int var16;
            try (
               Statement var3 = this.connection.createStatement();
               ResultSet var4 = var3.executeQuery(ckx.pC(new byte[]{16, 42, 60, 60, 49, 61, 71, 66, 84, 102, 122, 44, 100, 0, 84, 79, 92, 93, 88, 83}, 2, 162));
            ) {
               ArrayList var1 = new ArrayList();

               while (var4.next()) {
                  Object[] var10000 = new Object[]{var4.getString("eventname")};
                  var16 = var4.getInt("timestamp");
                  String var6 = var4.getString("version");
                  String var7 = var4.getString("eventname");
                  Map var8 = Strings.decodeMap(var4.getString("properties"));
                  var1.add(new TelemetryRecord(var16, var6, var7, var8));
               }

               if (!var1.isEmpty()) {
                  var3.execute(ckx.pC(new byte[]{7, 42, 60, 60, 38, 44, 71, 46, 38, 111, 101, 67, 76, 86, 84, 87, 77, 64}, 2, 181));
                  return this.endpoint.dump(var1);
               }

               var16 = 1;
            } catch (SQLException var14) {
               this.errcnt++;
               logger.catchingSilent(var14);
               return false;
            }

            return (boolean)var16;
         }
      }
   }
}
