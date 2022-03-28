/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pinot.server.starter.helix;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class SegmentLocks {
  private static final int NUM_LOCKS = 10000;
  private static final Lock[] LOCKS = new Lock[NUM_LOCKS];

  static {
    for (int i = 0; i < NUM_LOCKS; i++) {
      LOCKS[i] = new ReentrantLock();
    }
  }

  public static Lock getSegmentLock(String tableNameWithType, String segmentName) {
    return LOCKS[Math.abs((31 * tableNameWithType.hashCode() + segmentName.hashCode()) % NUM_LOCKS)];
  }
}
