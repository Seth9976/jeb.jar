package com.pnfsoftware.jeb.core.units.code.cuda;

public interface ICudaCubinEntry {
   int KIND_PTX = 1;
   int KIND_SASS = 2;
   int FLAG_AddressSize64 = 1;
   int FLAG_HasDebugInfo = 2;
   int FLAG_ProducerCuda = 4;
   int FLAG_HostLinux = 16;
   int FLAG_HostMac = 32;
   int FLAG_HostWindows = 64;
   int FLAG_CompressionLZ4 = 8192;
   int FLAG_CompressionZstd = 32768;

   int getKind();

   int getVersionMajor();

   int getVersionMinor();

   int getCudaArch();

   int getFlags();

   byte[] getData();
}
