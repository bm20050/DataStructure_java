package ch03;

//방법1
	//Arrays.sort(arr, (a,b) -> a.getPrice() - b.getPrice()); //Fruit에 compareTo()가 있어도 람다식 우선 적용
	/* 
	 *
 //방법2
	Arrays.sort(arr, new Comparator<Fruit>() {
	      @Override
	      public int compare(Fruit a1, Fruit a2) {
	         //return a1.getPrice() - a2.getPrice();
	    	  return a1.getName().compareTo(a2.getName());
	      }
	   });
	   */
//방법3
//    Comparator<Fruit> cc1 = new Comparator<Fruit>() {//익명클래스 사용 
//        public int compare(Fruit u1, Fruit u2) {
//          return u1.compareTo(u2);
//        }
//      };  
//      Arrays.sort(arr,cc1);