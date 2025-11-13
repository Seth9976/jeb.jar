package com.pnfsoftware.jeb.core;

public interface IInputRecord {
   long getSize();

   byte[] getContentsSha256();

   int getSeenCount();
}
