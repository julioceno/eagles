import { makeAutoObservable } from "mobx"

class Count {
  count = 0

  constructor() {
      makeAutoObservable(this)
  }

  increase() {
      this.count += 1
  }
}

export const count = new Count()