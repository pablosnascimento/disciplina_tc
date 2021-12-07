teste-if-only.txt
{:expr :if
	:test {:expr :input :index 0}
	:then {:expr :assign
		   :lhs {:expr :var :name "a"}
		   :rhs {:expr :funcall :name "AND"
				 :args [{:expr :input :index 1}
						{:expr :input :index 2}]}}}

Equivale a:
if X[0]:
a = AND(X[1], X[2])
end

Transformar em:
a$then1 = AND(X[1], X[2])
a = IF(X[0],a$then1,a)

[{:expr :assign
   :lhs {:expr :var :name "a$then1"}
   :rhs {:expr :funcall :name "AND"
		 :args [{:expr :input :index 1}
				{:expr :input :index 2}]}},
 {:expr :assign
	:lhs {:expr :var :name "a"}
    :rhs {:expr :funcall :name "IF"
		 :args [{:expr :input :index 0} //test do if original
				{:expr :var :name "a$then1}
				{:expr :var :name "a"}]}}
======================================================================
teste-if-only2.txt
{:expr :if
	:test {:expr :input :index 0}
	:then [{:expr :assign
		    :lhs {:expr :var :name "a"}
		    :rhs {:expr :funcall :name "AND"
				 :args [{:expr :input :index 1}
						{:expr :input :index 2}]}}
		   {:expr :assign
		    :lhs {:expr :var :name "b"}
		    :rhs {:expr :funcall :name "XOR"
				 :args [{:expr :input :index 1}
						{:expr :input :index 2}]}}]}

Equivale a:
if X[0]:
a = AND(X[1], X[2])
b = XOR(X[1], X[2])
end

Transformar em:
a$then1 = AND(X[1], X[2])
b$then1 = XOR(X[1], X[2])
a = IF(X[0],a$then1,a)
b = IF(X[0],b$then1,b)

[{:expr :assign
   :lhs {:expr :var :name "a$then1"}
   :rhs {:expr :funcall :name "AND"
		 :args [{:expr :input :index 1}
				{:expr :input :index 2}]}},
 {:expr :assign
	:lhs {:expr :var :name "a"}
    :rhs {:expr :funcall :name "IF"
		  :args [{:expr :input :index 0} //test do if original
				{:expr :var :name "a$then1}
				{:expr :var :name "a"}]}},
 {:expr :assign
	:lhs {:expr :var :name "b"}
    :rhs {:expr :funcall :name "IF"
		 :args [{:expr :input :index 0} //test do if original
				{:expr :var :name "b$then1}
				{:expr :var :name "b"}]}}]
=========================================================================				

teste-if-else.txt
[{:expr :if
	:test {:expr :input :index 0}
	:then {:expr :assign
		   :lhs {:expr :var :name "a"}
		   :rhs {:expr :funcall :name "AND"
				 :args [{:expr :input :index 1}
						{:expr :input :index 2}]}}
	:else {:expr :assign
		   :lhs {:expr :var :name "a"}
		   :rhs {:expr :funcall :name "NAND"
				 :args [{:expr :input :index 1}
						{:expr :input :index 2}]}}}]

Equivale a:
if X[0]:
a = AND(X[1], X[2])
else:
a = NAND(X[1], X[2])
end


Transformar em:
a$then1 = AND(X[1], X[2])
a$else1 = NAND(X[1],X[2])
a = IF(X[0],a$then1,a$else1)

[{:expr :assign
   :lhs {:expr :var :name "a$then1"}
   :rhs {:expr :funcall :name "AND"
		 :args [{:expr :input :index 1}
				{:expr :input :index 2}]}},
				
 {:expr :assign
   :lhs {:expr :var :name "a$else1"}
   :rhs {:expr :funcall :name "NAND"
		 :args [{:expr :input :index 1}
				{:expr :input :index 2}]}},
				
 {:expr :assign
	:lhs {:expr :var :name "a"}
    :rhs {:expr :funcall :name "IF"
		 :args [{:expr :input :index 0} //test do if original
				{:expr :var :name "a$then1}
				{:expr :var :name "a$else1"}]}}